## IEIMS Guide for Writing Message Properties

### Common Texts
What constitutes a `common text`?  
Any message value that is not specific to any single feature can be considered as a common text. We 
should always use the `common` prefix with these message keys.

#### Examples
```properties
common.continue=Continue
common.permission.categories=Permission Categories
```


### Error
For all kinds of error texts, let's use the `error` prefix with the message keys.

#### Examples
```properties
error.could.not.update.email.address=Couldn't update email address
error.request.denied=Sorry. Your request was denied!
```


### Feature Sections
We already have several sections such as `Menu and Side Menu`, `Datatable`, `Role`, `Password`, etc.
We will obviously need to add new sections as we develop more features. All keys related to a 
feature should be prefixed with the feature name and be grouped together. It is also helpful if we add a clearly visible separator between
sections.

#### Examples
```properties
########################### ROLE #################################
role.select.new.role=Select New Role
role.permission=Role Permission
# MORE
########################### USER #################################
user.create=Create User
user.creating=Creating User
# MORE
```

Preferably, we will try to order the keys in a manner so that it seems like they tell us a story.
Let's have a look at the example below:
```properties
cluster.update=Update Cluster
cluster.updating=Updating Cluster
cluster.update.confirm.message=This will update cluster. Please press confirm to continue.
cluster.update.success.with.name=You have successfully updated cluster <b>{0}</b>
cluster.delete=Delete Cluster
cluster.delete.message=This will delete the following cluster. Please press confirm to continue.
cluster.active.delete.alert=You can't delete any cluster which is already in use
cluster.delete.success.with.name=You have successfully deleted cluster <b>{0}</b>
```

By reading the message keys sequentially, we should have an idea what are the possible scenarios a 
user might encounter while trying to update or delete a cluster.


### Others
We need to add all routes to the `permission_entry` table. This table has a `title_key` column which
contains message keys. Often times, we do not use these keys anywhere else in our application except
for the `Role Creation` page (permission list).

It doesn't make sense to show all these keys in the `permission list`. Let's look at an example to
understand this issue better:

Suppose we allow our users to update their password. This action, to the very least, requires two 
http endpoints, `GET` and `POST`. 

```sql
INSERT INTO PERMISSION_ENTRY (permission_entry_id, title_key, endpoint, http_method)
VALUES (10, 'user.change.password', '/user/me/change-password', 'GET');
INSERT INTO PERMISSION_ENTRY (permission_entry_id, title_key, endpoint, http_method)
VALUES (11, 'user.change.password.submit', '/user/me/change-password', 'POST');
```

The `title_key` column requires unique values. Hence, we cannot use the same key for both these 
entries. Notice the difference between keys- `user.change.password` and `user.change.password.submit`.  

In the `Role Creation` page, under the `permission list`, we display both these entries. It looks
like this:
- Change Password
- Change Password Submit

Now we see why we should do something about it. However, for the time being, the keys which are used
only in the permission list are grouped separately under the following header:
```properties
########################### DB MAPPINGS #################################
login.authenticate=User Login Authenticate
login.onboard=User Onboard
error.page=Error Page
new.own.password=Change Own Password
change.password.as.admin=Change Password as Admin
upload.file=Upload File
# MORE
```

### Important Notes
1. Please make sure that, for any key, the line numbers match in the `message.properties` and, 
   `message_bn.properties` files.
   
2. Do not use `th:utext` for fields inputted by users as they are prone to javascript injection.
