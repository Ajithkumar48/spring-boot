package com.example.demo.greeting.controller;

import com.example.demo.greeting.dto.GreetResponse;
import com.example.demo.greeting.dto.GreetingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting") // Changed to be more specific
public class GreetingController {

    @Operation(
            summary = "Test Get Operation",
            operationId = "demoGetControllerV1",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response",
                            content = @Content(mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                            {
                                                               "message": "Greetings from Spring Boot!"
                                                             }
                                                        """
                                            )
                                    })
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(mediaType = "text/plain",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                           Internal Server Error
                                                     """
                                            )
                                    })
                    )
            }
    )
    @GetMapping("/test")
    public GreetingResponse index() {
        return new GreetingResponse("Greetings from Spring Boot!");
    }

    @Operation(
            summary = "Test Post Operation",
            operationId = "demoPostControllerV1",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response",
                            content = @Content(mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                            {
                                                              "message": "Hello, User! This is a POST request."
                                                            }
                                                        """
                                            )
                                    })
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(mediaType = "text/plain",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                           Internal Server Error
                                                     """
                                            )
                                    })
                    )
            }
    )
    @PostMapping("/greet")
    public GreetResponse greet(@RequestParam String name) {
        return new GreetResponse("Hello, " + name + "! This is a POST request.");
    }
}
