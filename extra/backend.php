<?php
error_log('Start!');
sleep(10);

echo '<h1>Backend response</h1>';
print_r($_SERVER);
$request = file_get_contents('php://input');
error_log('End');
