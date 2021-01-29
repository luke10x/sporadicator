var AWS = require('aws-sdk');
AWS.config.update({
  region: 'us-west-1',
  accessKeyId: 'accessKeyId',
  secretAccessKey: 'secretAccessKey',
  endpoint: new AWS.Endpoint('http://sqs:9324/'),
});
var sqs = new AWS.SQS({ apiVersion: '2012-11-05' });

const RESPONSE_QUEUE_URL = "http://sqs:9324/queue/bus-events"
const REQUEST_QUEUE_URL = "http://sqs:9324/queue/bus-commands";

const convert = (request) => {
  return {
    originalRequestMessageId: request.MessageId,
    response: Buffer.from(request.Body).toString('base64'),
    originalRequest: request.Body
  }
}

const respond = (data) => {
  sqs.sendMessage(
    { MessageBody: JSON.stringify(data), QueueUrl: RESPONSE_QUEUE_URL },
    (err, data) => {
      if (err) {
        console.error("Error", err);
      } else {
        console.log("Success", data.MessageId);
      }
    }
  );
}

const process = () => {
  sqs.receiveMessage(
    {
      MaxNumberOfMessages: 10,
      QueueUrl: REQUEST_QUEUE_URL,
      VisibilityTimeout: 20,
      WaitTimeSeconds: 5,
    },
    (err, data) => {
      if (err) {
        console.Error("Receive Error", err);
      } else if (data.Messages) {
        console.log('🚌 command:', data);

        sqs.deleteMessage(
          {
            QueueUrl: REQUEST_QUEUE_URL,
            ReceiptHandle: data.Messages[0].ReceiptHandle
          },
          (err, data) => {
            if (err) {
              console.error("Delete Error", err);
            }
          }
        );

        data.Messages.map(request => respond(convert(request)));
      }
    }
  );
}

setInterval(process, 3000);
