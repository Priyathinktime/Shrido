
Feature: User Tests  
This feature includes User tests using RESTFul services

Background:
Given The User sets the base URL as "https://shrido-staging-fxfyn.ondigitalocean.app"

@UserTest
Scenario: TC-01 Register a New User Successfully
When The User sends a POST request with the request body from "user register.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key     | Value                         |
  | code    | 201                           |
  | status  | true                          |
  | message | User Registered Successfully. |

@UserTest
Scenario: TC-02 Retrieve Referral History
When The User sends a GET request from "Get Referral History" and captures the response body
Then The response code should be "200"
And The response body should contain the following key-value pairs:
  | Key     | Value             |
  | code    | 200               |
  | status  | true              |
  | message | Referral History. |

@UserTest
Scenario: TC-03 Retrieve Total Referrals
When The User sends a GET request from "Get Total Referrals" and captures the response body
Then The response code should be "200"
And The response body should contain the following key-value pairs:
  | Key      | Value                     |
  | code     | 200                       |
  | status   | true                      |
  | message  | Total Referral Points.    |

@UserTest
Scenario: TC-04 Verify OTP
When The User sends a POST request with the request body from "Otp verify.json" and captures the response body
Then The response code should be "200"
And The response body should contain the following key-value pairs:
  | Key      | Value                      |
  | code     | 200                        |
  | status   | true                       |
  | message  | Otp Verified Successfully. |

@UserTest
Scenario: TC-05 Resend OTP
When The User sends a POST request with the request body from "Resend Otp" and captures the response body
Then The response code should be "200"
And The response body should contain the following key-value pairs:
  | Key       | Value                      |
  | code      | 200                        |
  | status    | true                       |
  | message   | Otp sent successfully.     |

Scenario: TC-06 Assign Role
When The User sends a POST request with the request body from "Add Role.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key     | Value    |
  | status  | true     |
  | role    | customer |

Scenario: TC-07 Setup Profile
When The User sends a POST request with the request body from "Profile_Setup.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key       | Value    |
  | status    | true     |
  | profileId | 101      |

Scenario: TC-08 Apply Referral Code
When The User sends a POST request with the request body from "Add Referral Code.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key          | Value    |
  | status       | true     |
  | referralUsed | true     |

Scenario: TC-09 Add Car Details
When The User sends a POST request with the request body from "Add car Detail.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key        | Value      |
  | status     | true       |
  | carType    | Sedan      |
  | regNumber  | TN01AB1234 |

Scenario: TC-10 Submit Driver Acknowledgement
When The User sends a POST request with the request body from "Driver Acknowledged.json" and captures the response body
Then The response code should be "201"
And The response body should contain the following key-value pairs:
  | Key            | Value   |
  | status         | true    |
  | acknowledged   | true    |