<?php
error_log('Start!');
sleep(10);

$method = $_SERVER['REQUEST_METHOD'];
$uri = $_SERVER['REQUEST_URI'];
$body = file_get_contents('php://input');

echo '<h1>Backend response</h1>';
echo "request method: ${method}<br>\n";
echo "request URI: ${uri}<br>\n";
echo "request body: ${body}<br>\n";

echo "<h2>headers</h2>\n";
foreach (getallheaders() as $name => $value) {
    echo "$name: $value<br>\n";
}

error_log('End');
