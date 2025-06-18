# Add Expense
curl -X POST http://localhost:8082/easysplit/expense/add \
-H "Content-Type: application/json" \
-d '{
  "amount": 1000.0,
  "payeeUserName": "user1",
  "splits": [
    {
      "user": { "userName": "user1" },
      "amount": 500.0
    },
    {
      "user": { "userName": "user2" },
      "amount": 500.0
    }
  ]
}'

# Get All Balances
curl http://localhost:8082/easysplit/expense/balance/all

# Get Balances for a User (replace 'user1' with the desired username)
curl http://localhost:8082/easysplit/expense/balance/user1

# Get Default User
curl http://localhost:8082/easysplit/user/default

# Register User
curl -X POST http://localhost:8082/easysplit/user/register \
-H "Content-Type: application/json" \
-d '{
  "userName": "newUser",
  "name": "New User",
  "email": "newuser@example.com",
  "phoneNumber": "1234567890"
}'

# Get User by Username (replace 'user1' with the desired username)
curl http://localhost:8082/easysplit/user/get?userName=user1

# Get All Users
curl http://localhost:8082/easysplit/user/get/all

# Update User (replace 'userToUpdate' with the desired username and update details)
curl -X POST http://localhost:8082/easysplit/user/update \
-H "Content-Type: application/json" \
-d '{
  "userName": "userToUpdate",
  "name": "Updated Name",
  "email": "updatedemail@example.com",
  "phoneNumber": "0987654321"
}'
