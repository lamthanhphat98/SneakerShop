<%-- 
    Document   : register
    Created on : Oct 28, 2018, 5:12:41 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <title>Phat SneakerShop</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
        <script src="G:\\ProjectJava\\SneakerShop\\web\\WEB-INF\\js\\request.js"></script>

    </head>

    <body>

        <!-- Top menu -->
        <nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->

            </div>
        </nav>

        <!-- Top content -->
        <div class="top-content">

            <div class="inner-bg">
                <div class="container">

                    <div class="row">
                        <div class="col-sm-6 book">
                            <img src="images/kingshoes.jpg" alt=""   height="250" width="450" style="margin-top: 20px;">
                               <img src="images/DVCTSXL.png" height="250" width="450" alt="Promo">
                               <img src="images/centimet.jpg"  height="250" width="450" alt=""/>
                        </div>
                        <div class="col-sm-5 form-box">
                            <div class="form-top">
                                <div class="form-top-left">
                                    <h3>Become our friends</h3>
                                    <p>Fill in the form below to get instant access:</p>
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-pencil"></i>
                                </div>
                            </div>
                            <div class="form-bottom">
                                <form  action="MainController" method="post" class="registration-form">
                                    <div class="form-group">
                                        <label class="sr-only" for="form-first-name">Username</label>
                                        <input type="text" id="txtUsername" name="txtUsername" placeholder="Username.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorUsername" style="color:red"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-last-name">Password</label>
                                        <input type="password" id="txtPassword" name="txtPassword" type="password" placeholder="Password.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorPassword" style="color:red"/>

                                        <!--<input type="text" name="form-last-name" placeholder="password..." class="form-last-name form-control" id="form-last-name">-->
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-last-name">Password</label>
                                        <input type="password" id="txtConfirmPassword" name="txtConfirmPassword" type="password" placeholder="Password.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorConfirmPassword" style="color:red"/>

                                        <!--<input type="text" name="form-last-name" placeholder="password..." class="form-last-name form-control" id="form-last-name">-->
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-last-name">Full Name</label>
                                        <input type="text" id="txtFullname" name="txtFullname" placeholder="Fullname.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorFullname" style="color:red"/>

                                        <!--<input type="text" name="form-last-name" placeholder="fullname..." class="form-last-name form-control" id="form-last-name">-->
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-email">Email</label>
                                        <input type="text" id="txtEmail" name="txtEmail" placeholder="Email.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorEmail" style="color:red" />

                                        <!--<input type="text" name="form-email" placeholder="email..." class="form-email form-control" id="form-email">-->
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-email">Phone</label>
                                        <input type="text" id="txtPhone" name="txtPhone" placeholder="Phone.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorPhone" style="color:red"/>

                                        <!--<input type="text" name="form-email" placeholder="email..." class="form-email form-control" id="form-email">-->
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-email">Phone</label>
                                        <input type="text" id="txtAddress" name="txtAddress" placeholder="Address.." class="form-first-name form-control" id="form-first-name"/>
                                        <label id="errorAddress" style="color:red"/>

                                        <!--<input type="text" name="form-email" placeholder="email..." class="form-email form-control" id="form-email">-->
                                    </div>
                                    <button onclick="validate();" class="btn" value="register" name="action">Submit</button>
                                </form>
                                <script>
                                    function validate()
                                    {
                                        var validate = true;
                                        var username = document.getElementById('txtUsername').value;
                                        var password = document.getElementById('txtPassword').value;
                                        var confirmPassword = document.getElementById('txtConfirmPassword').value;
                                        var fullName = document.getElementById('txtFullname').value;
                                        var phone = document.getElementById('txtPhone').value;
                                        var address = document.getElementById('txtAddress').value;
                                        var email = document.getElementById('txtEmail').value;

                                        if (username.length == 0)
                                        {
                                            var errorUsername = document.getElementById('errorUsername');
                                            errorUsername.innerText = 'Username required'
                                            validate = false;
                                        }
                                        if (password.length == 0)
                                        {
                                            var errorPassword = document.getElementById('errorPassword');
                                            errorPassword.innerText = 'Password required'
                                            validate = false;

                                        }
                                        if (email.length == 0)
                                        {
                                            var errorEmail = document.getElementById('errorEmail');
                                            errorEmail.innerText = 'Email required'
                                            validate = false;

                                        }
                                        if (phone.length == 0)
                                        {
                                            var errorPhone = document.getElementById('errorPhone');
                                            errorPhone.innerText = 'Phone required'
                                            validate = false;

                                        }
                                        if (confirmPassword.length == 0)
                                        {
                                            var errorConfirm = document.getElementById('errorConfirmPassword');
                                            errorConfirm.innerText = 'Confirm required'
                                            validate = false;

                                        }
                                        if (fullName.length == 0)
                                        {
                                            var errorFullname = document.getElementById('errorFullname');
                                            errorFullname.innerText = 'Name required'
                                            validate = false;

                                        }
                                        if (address.length == 0)
                                        {
                                            var errorAddress = document.getElementById('errorAddress');
                                            errorAddress.innerText = 'Address required'
                                            validate = false;

                                        }
//                                        if (validate)
//                                        {
//                                            request({
//                                                action: 'register',
//                                                username: username,
//                                                password: password,
//                                                fullName: fullName,
//                                                email: email,
//                                                phone: phone,
//                                                address: address
//                                            });
//                                        }
//                                        console.log(username);
                                    }

                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/retina-1.1.0.min.js"></script>
        <script src="assets/js/scripts.js"></script>

        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>
