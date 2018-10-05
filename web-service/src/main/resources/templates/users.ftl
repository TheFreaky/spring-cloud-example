<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Photo</th>
                </tr>
                </thead>
                <tbody>
                <#if users??>
                    <#list users as user>
                        <tr>
                        <#--<td>${user.id}</td>-->
                            <td scope="row">${user.name}</td>
                            <td><img src="${user.imgUrl}" class="img-responsive img-thumbnail"></td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>