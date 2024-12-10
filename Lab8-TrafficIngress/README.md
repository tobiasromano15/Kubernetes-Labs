Para poder utilizar el service de loadbalancer en una consola local utilizando minikube, es importante usar un tunnel con:

- minikube tunnel

para poder simular un balanceador de cargar y asignar una ip externa, de lo contrario no va a funcionar.

Para acceder a los curls que indica el lab:
curl http://localhost -> Para probar el ingress-nginx
curl http://localhost:8111 -> Probar devsecops-loan-loadbalancer

usar la ip del cluster, en este caso minikube.

- minikube ip