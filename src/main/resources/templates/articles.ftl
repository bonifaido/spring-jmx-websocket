<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Saved Articles</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>
<div class="container">

    <h2>Saved Articles</h2>

    <div class="panel-group" id="accordion">

        <#list articles as article>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${article_index}">
                            ${article.title}
                        </a>
                    </h4>
                </div>
                <div id="collapse${article_index}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <a href="${article.href}">${article.text}</a>
                    </div>
                </div>
            </div>

        </#list>

    </div>

</div>

<script src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>