<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Create Account - Cyber Threat Reporting System</title>

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

        /* CONTAINER */

        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
        }

        .register-card {
            background: white;
            width: 100%;
            max-width: 460px;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 35px rgba(0,0,0,0.25);
        }

        .register-card h2 {
            text-align: center;
            color: #092b57;
            margin-bottom: 8px;
        }

        .subtitle {
            text-align: center;
            color: #666;
            font-size: 14px;
            margin-bottom: 28px;
        }

        /* FORM */

        .form-group {
            margin-bottom: 18px;
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

        .hint {
            color: #777;
            font-size: 12px;
            margin-top: 5px;
        }

        .register-button {
            width: 100%;
            padding: 14px;
            background: #092b57;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 5px;
        }

        .register-button:hover {
            background: #1769aa;
        }

        /* LOGIN */

        .login-link {
            text-align: center;
            margin-top: 22px;
            color: #666;
            font-size: 14px;
        }

        .login-link a {
            color: #1769aa;
            font-weight: bold;
            text-decoration: none;
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

            .register-card {
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


<!-- REGISTER -->

<div class="container">

    <div class="register-card">

        <h2>Create Account</h2>

        <p class="subtitle">
            Create an account to report and track cyber threats
        </p>


        <form action="register" method="post">

            <div class="form-group">

                <label for="name">
                    Full Name
                </label>

                <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Enter your full name"
                    required>

            </div>


            <div class="form-group">

                <label for="email">
                    Email Address
                </label>

                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Enter your email address"
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
                    placeholder="Create a password"
                    minlength="6"
                    required>

                <div class="hint">
                    Password must contain at least 6 characters.
                </div>

            </div>


            <button
                type="submit"
                class="register-button">

                Create Account

            </button>

        </form>


        <div class="login-link">

            Already have an account?

            <a href="login.jsp">
                Login
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