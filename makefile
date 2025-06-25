run:
	docker compose start
	docker ps

up:
	docker compose up -d --build
	docker ps
stop:
	docker compose stop
	docker ps -a

clean:
	docker compose down -v 
	docker ps
	docker volume ls
