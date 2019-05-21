package backEnd.service.impl;

import backEnd.AppPropertiesConfig;
import backEnd.dao.UserDAO;
import backEnd.model.User;
import backEnd.request.UserRequest;
import backEnd.response.Address;
import backEnd.response.ApiResponse;
import backEnd.response.UserResponse;
import backEnd.response.UsersResponse;
import backEnd.service.NotificationService;
import backEnd.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AppPropertiesConfig config;

    @Override
    public UserResponse createUser(UserRequest request, String ipAddress) {
        User user = new User.Builder().email(request.getEmail())
                .date(new Date()).firstName(request.getFirstName()).lastName(request.getLastName()).wallet(request.getWallet())
                .language(request.getLanguage()).country(request.getCountry()).build();
        userDAO.saveUser(user);

        notificationService.sendInvestorGreeting(request.getEmail(), request.getLanguage());

        UserResponse response = new UserResponse();
        response.setUser(user);
        response.setMessage("New user was created");
        response.setStatus(ApiResponse.Status.ok.toString());
        return response;
    }

    @Override
    public UsersResponse getAllUsers(Integer top, Integer skip) {
        UsersResponse response = new UsersResponse();
        List<User> users = userDAO.getUsers(top, skip);
        response.setUsers(users);
        response.setStatus(ApiResponse.Status.ok.toString());
        response.setMessage("List of all users");
        return response;
    }

    @Override
    public void exportUsersInExcel() {
        List<User> request = userDAO.getUsers(0, 0);
        try {
            String excelPath = "D:\\Users.xls";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(excelPath));

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Users");

            Object[][] users = new Object[request.size()][];
            for (int i = 0; i < request.size(); i++) {
                User user = request.get(i);
                users[i] = new Object[]{
                        user.getId(),
                        user.getEmail(),
                        user.getLanguage(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getCountry(),
                        user.getWallet(),
                        user.getDate()};
            }

            int rowNumber = 0;
            for (Object[] user : users) {
                Row row = sheet.createRow(rowNumber++);

                int column = 0;
                for (Object value : user) {
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

    public ApiResponse kycCheck(MultipartFile multipartFile, String email, String extension) throws IOException {
        User user = userDAO.getByEmail(email);
        File attachment = convertFromMultiPart(multipartFile);

        if (user != null) {
            notificationService.sendAdminKYCCheck(attachment, user, extension);
        }

        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.Status.ok.toString());
        response.setMessage("Email was sent");
        return response;
    }

    @Override
    public Address getCountryByIP(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder businessBuilder = UriComponentsBuilder
                .fromUriString(config.ipApiRequestUrl + ipAddress + "?access_key=" +
                        config.ipApiAccessKey);

        ResponseEntity<Address> response = restTemplate.getForEntity(businessBuilder.build().toString(), Address.class);
        return response.getBody();
    }

    @Override
    public ApiResponse readFile(String fileAddress) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(fileAddress));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
        }
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.Status.ok.toString());
        response.setMessage("File was read");
        return response;
    }

    private File convertFromMultiPart(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
