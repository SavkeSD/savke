### Pay atention on sidecar:
When install sidecar on linux, we also need to install and collector (filebeat)
When install sidecar on Windows, filebeat come with sidecar. So if we would like to use filebeat, don't need to install it, bcs it
is already installed. But if we would like to use some other collector (winlog for example), we need to install it.

### Pay atention on generating sha passwords, and password secret when installing graylog -- THIS NEED TO BE DONE BEFORE DEPLOY !!!!

1.) password_secret -- you will need to generate a secret to secure the user passwords  -- you can do that with 
    ```pwgen -N 1 -s 96```  -- output of this command need to be stored in variable {{ vault_password_secret }}

2.) You will also need to generate a secure password for Graylog admin user -- you can do that with
    ```echo -n password | sha256sum```     -- output is like (5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8, this is just example, dont use this)
    output should be stored in variable {{ vault_admin_password_sha }}
