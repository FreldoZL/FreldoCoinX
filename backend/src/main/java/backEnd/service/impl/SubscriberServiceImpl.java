package backEnd.service.impl;

import backEnd.dao.SubscriberDAO;
import backEnd.model.Subscriber;
import backEnd.model.User;
import backEnd.request.SubscriberRequest;
import backEnd.response.ApiResponse;
import backEnd.response.SubscribersResponse;
import backEnd.service.NotificationService;
import backEnd.service.SubscriberService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberDAO subscriberDAO;

    @Autowired
    private NotificationService notificationService;

    @Override
    public ApiResponse saveSubscribe(SubscriberRequest request) {
        Subscriber subscriber = new Subscriber.Builder().email(request.getEmail()).language(request.getLanguage()).build();
        subscriberDAO.saveSubscriber(subscriber);
        notificationService.sendSubscribeGreeting(request.getEmail(), request.getLanguage());

        return new ApiResponse(ApiResponse.Status.ok.name(), "Subscriber was saved");
    }

    @Override
    public SubscribersResponse getSubscribers(Integer top, Integer skip) {
        SubscribersResponse response = new SubscribersResponse();
        List<Subscriber> subscribers = subscriberDAO.getSubscribers(top, skip);

        response.setSubscribers(subscribers);
        response.setStatus(ApiResponse.Status.ok.name());
        response.setMessage("List of all subscribers");
        return response;
    }

    @Override
    public void exportSubscribersInExcel() {
        List<Subscriber> request = subscriberDAO.getSubscribers(0, 0);
        try {
            String excelPath = "D:\\Subscribers.xls";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(excelPath));

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Users");

            Object[][] subscribers = new Object[request.size()][];
            for (int i = 0; i < request.size(); i++) {
                Subscriber subscriber = request.get(i);
                subscribers[i] = new Object[]{
                        subscriber.getId(),
                        subscriber.getEmail(),
                        subscriber.getLanguage()};
            }

            int rowNumber = 0;
            for (Object[] subscriber : subscribers) {
                Row row = sheet.createRow(rowNumber++);

                int column = 0;
                for (Object value : subscriber) {
                    Cell cell = row.createCell(column++);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    }
                }
            }
            workbook.write(fileOutputStream);
            workbook.close();

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
