package com.springdemo.cqrs.user.query.api.controller;

import com.springdemo.cqrs.user.query.api.dto.UserLookupResponse;
import com.springdemo.cqrs.user.query.api.query.FindAllUsersQuery;
import com.springdemo.cqrs.user.query.api.query.FindUserByIdQuery;
import com.springdemo.cqrs.user.query.api.query.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/userlookup")
public class UserLookupController {

    QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getAllUser()
    {
        try{

            var response =queryGateway.query(new FindAllUsersQuery(), ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(response == null || response.getUsers() == null)
            {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new UserLookupResponse("error in perfoming user lookup"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping (path = "/byid")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getUserById(@RequestParam(name = "id") String id)
    {
        try{
            var query = new FindUserByIdQuery(id);
            var response =queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(response == null || response.getUsers() == null)
            {
              return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new UserLookupResponse("error in perfoming user lookup"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byfilter")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getUserByFilter(@RequestParam (name = "filter") String filter)
    {
        try{
            var query = new SearchUsersQuery(filter);
            var response =queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(response == null || response.getUsers() == null)
            {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new UserLookupResponse("error in perfoming user lookup"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
