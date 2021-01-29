list-queues:
	docker-compose run --rm aws --endpoint-url http://sqs:9324 sqs \
		list-queues

receive-commands:
	docker-compose run --rm aws --endpoint-url http://sqs:9324 sqs \
		receive-message --queue-url "http://sqs:9324/queue/bus-commands" \
			--max-number-of-messages 10

receive-events:
	docker-compose run --rm aws --endpoint-url http://sqs:9324 sqs \
		receive-message --queue-url "http://sqs:9324/queue/bus-events" \
			--max-number-of-messages 10

send-command:
	docker-compose run --rm aws --endpoint-url http://sqs:9324 sqs \
		send-message --queue-url "http://sqs:9324/queue/bus-commands" \
			--message-body "CMD: $$(date)"

send-event:
	docker-compose run --rm aws --endpoint-url http://sqs:9324 sqs \
		send-message --queue-url "http://sqs:9324/queue/bus-events" \
			--message-body "EVT: $$(date)"

start-bus:
	docker-compose up -d --force-recreate --build bus

start-sqs:
	docker-compose up -d --force-recreate --build sqs

start: start-sqs start-bus

logs:
	docker-compose logs -f

stop:
	docker-compose down --remove-orphans

bash-bus:
	docker-compose run --rm bus bash

unrootify:
	sudo chown -R $$(id -u):$$(id -g) .

