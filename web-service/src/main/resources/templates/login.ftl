<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form action="/ui/login" method="post">
                <div class="form-group">
                    <label for="username">Enter username:</label>
                    <input type="text" name="username" class="form-control" placeholder="Your username here..."/>
                </div>
                <div class="form-group">
                    <label for="password">Enter password:</label>
                    <input type="password" name="password" class="form-control" placeholder="Your password here..."/>
                </div>
                <button type="submit" class="btn btn-default">Sign in</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>