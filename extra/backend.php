<?php
error_log('Start!');
sleep(10);

$method = $_SERVER['REQUEST_METHOD'];
$uri = $_SERVER['REQUEST_URI'];
$body = file_get_contents('php://input');

echo '<h1>Backend response</h1>';
echo "request method: ${method}\n";
echo "request URI: ${uri}\n";
echo "request body: ${body}\n";

foreach (getallheaders() as $name => $value) {
    echo "$name: $value\n";
}

error_log('End');
