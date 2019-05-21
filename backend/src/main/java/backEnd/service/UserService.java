package backEnd.service;

import backEnd.request.UserRequest;
import backEnd.response.Address;
import backEnd.response.ApiResponse;
import backEnd.response.UserResponse;
import backEnd.response.UsersResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    UserResponse createUser(UserRequest request, String ipAddress);

    UsersResponse getAllUsers(Integer top, Integer skip);

    void exportUsersInExcel();

    ApiResponse kycCheck(MultipartFile file, String email, String extension) throws IOException;

    Address getCountryByIP(String ipAddress);

    ApiResponse readFile(String fileAddress) throws IOException;
}
