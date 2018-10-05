<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form action="/ui/register" method="post">
                <div class="form-group">
                    <label for="connect">Enter name:</label>
                    <input id="name" type="text" name="name" class="form-control" placeholder="Your name here..."/>
                    <input id="connect" class="btn" type="button" value="Ok">
                </div>
                <div class="form-group">
                    <label for="image">Image:</label>
                    <img src="" id="image" class="img-responsive img-thumbnail"/>
                    <input hidden id="image-input" name="imgUrl"/>
                </div>
                <button type="submit"  class="btn btn-default">Register</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>