<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Login - Cyber Threat Reporting System</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #092b57, #1769aa);
            display: flex;
            flex-direction: column;
        }

        /* HEADER */

        .header {
            background: white;
            padding: 18px 6%;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .logo {
            width: 48px;
            height: 48px;
            background: #092b57;
            color: white;
            border-radius: 7px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            font-size: 20px;
        }

        .brand h1 {
            color: #092b57;
            font-size: 20px;
        }

        .brand p {
            color: #666;
            font-size: 12px;
            margin-top: 3px;
        }

        .home {
            color: #092b57;
            text-decoration: none;
            font-size: 14px;
            font-weight: bold;
        }

        /* LOGIN AREA */

        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
        }

        .login-card {
            background: white;
            width: 100%;
            max-width: 430px;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 35px rgba(0,0,0,0.25);
        }

        .login-card h2 {
            text-align: center;
            color: #092b57;
            margin-bottom: 8px;
        }

        .subtitle {
            text-align: center;
            color: #666;
            font-size: 14px;
            margin-bottom: 30px;
        }

        /* ERROR */

        .error {
            background: #fee2e2;
            color: #991b1b;
            border: 1px solid #fecaca;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
        }

        /* FORM */

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            color: #333;
            font-size: 14px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 13px;
            border: 1px solid #ccd3dc;
            border-radius: 5px;
            font-size: 14px;
            outline: none;
        }

        input:focus {
            border-color: #1769aa;
            box-shadow: 0 0 0 2px rgba(23,105,170,0.1);
        }

        .login-button {
            width: 100%;
            padding: 14px;
            background: #092b57;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
        }

        .login-button:hover {
            background: #1769aa;
        }

        /* LINKS */

        .register-link {
            text-align: center;
            margin-top: 22px;
            color: #666;
            font-size: 14px;
        }

        .register-link a {
            color: #1769aa;
            font-weight: bold;
            text-decoration: none;
        }

        .back-link {
            text-align: center;
            margin-top: 15px;
        }

        .back-link a {
            color: #092b57;
            text-decoration: none;
            font-size: 13px;
        }

        /* FOOTER */

        footer {
            color: #dbe7f3;
            text-align: center;
            padding: 15px;
            font-size: 12px;
        }

        @media (max-width: 600px) {

            .header {
                padding: 15px 20px;
            }

            .brand h1 {
                font-size: 16px;
            }

            .brand p {
                display: none;
            }

            .login-card {
                padding: 30px 25px;
            }
        }

    </style>

</head>

<body>

<!-- HEADER -->

<div class="header">

    <div class="brand">

        <div class="logo">
            CT
        </div>

        <div>

            <h1>Cyber Threat Reporting</h1>

            <p>
                Cyber Threat Reporting & Tracking System
            </p>

        </div>

    </div>

    <a href="index.html" class="home">
        ← Home
    </a>

</div>


<!-- LOGIN -->

<div class="container">

    <div class="login-card">

        <h2>Secure Login</h2>

        <p class="subtitle">
            Login to access your cyber threat reporting dashboard
        </p>


        <% if (request.getParameter("error") != null) { %>

            <div class="error">
                Invalid email or password.
                Please try again.
            </div>

        <% } %>


        <form action="login" method="post">

            <div class="form-group">

                <label for="email">
                    Email Address
                </label>

                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Enter your email"
                    required>

            </div>


            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="Enter your password"
                    required>

            </div>


            <button
                type="submit"
                class="login-button">

                Login

            </button>

        </form>


        <div class="register-link">

            Don't have an account?

            <a href="register.jsp">
                Create Account
            </a>

        </div>


        <div class="back-link">

            <a href="index.html">
                Back to Home
            </a>

        </div>

    </div>

</div>


<!-- FOOTER -->

<footer>

    © 2026 Cyber Threat Reporting & Tracking System
    | Academic Project

</footer>

</body>

</html>