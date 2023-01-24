# Install - Docker
Run install.sh to create the docker images and start the containers. If you want to change the ports where the services
are running you have to edit the docker run commands in docker_install.sh like shown below:

```console
docker run -d -p <YOUR_PORT>:80 ... 
```

and the EXPOSE commands in the Dockerfile like shown below:

```console
EXPOSE 80
...
```

# Uninstall - Docker

You can delete the docker containers and image associated with it by running the docker_uninstall.sh script.
