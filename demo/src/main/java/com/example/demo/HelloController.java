package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api") // Set a base path for all endpoints in this controller
public class HelloController {

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

    // Custom class to represent the JSON response
    public static class GreetingResponse {
        private String message;

        public GreetingResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
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

    // Custom class to represent the JSON response
    public static class GreetResponse {
        private String message;

        public GreetResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
