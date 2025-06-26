run:
	docker compose start
	docker ps

up:
	docker compose up -d --build
	docker ps
stop:
	docker compose stop
	docker ps -a

down:
	docker compose down -v 
	docker ps -a
	docker volume ls
