package backEnd.controller;

import backEnd.exception.BadRequestException;
import backEnd.request.UserRequest;
import backEnd.response.Address;
import backEnd.response.ApiResponse;
import backEnd.response.UserResponse;
import backEnd.response.UsersResponse;
import backEnd.service.UserService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user")
@Api(description = "Services for users", name = "User controller")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method user to create user.")
    public @ResponseBody
    UserResponse createUser(@RequestBody UserRequest request, HttpServletRequest httpServletRequest){
        return userService.createUser(request, httpServletRequest.getRemoteAddr());
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to get all users")
    public UsersResponse getAllUsers(@RequestParam(value = "top", required = false, defaultValue = "10") Integer top,
                                     @RequestParam(value = "skip", required = false, defaultValue = "0") Integer skip) throws Exception {
        return userService.getAllUsers(top, skip);
    }

    @RequestMapping(value = "/export_users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to export all users in excel")
    public void exportUsersInExcel() {
        userService.exportUsersInExcel();
    }

    @RequestMapping(value = "/kyc_check", method = RequestMethod.POST)
    @ApiMethod(description = "Method uses to send user ID-card to admin for KYC check")
    public @ResponseBody
    ApiResponse kycCheck(@RequestPart("picture") MultipartFile file,
                         @RequestParam(value = "email") String email,
                         @RequestParam(value = "extension") String extension) throws BadRequestException, IOException {
        return userService.kycCheck(file, email, extension);
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to get country by ip-address")
    public Address getCountry(HttpServletRequest httpServletRequest) throws IOException {
        return userService.getCountryByIP(httpServletRequest.getRemoteAddr());
    }

    @RequestMapping(value = "/read_file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to read bounty information from .xlsx file")
    public ApiResponse readFile(@RequestParam(value = "fileAddress") String fileAddress) throws IOException {
        return userService.readFile(fileAddress);
    }
}
