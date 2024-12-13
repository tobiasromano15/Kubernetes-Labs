As a fintech company, we have been planning a Jenkins migration for months because we're hitting scalability limits with our CI/CD pipelines due to a higher-than-expected amount of new clients. We've finally decided that we will use Kubernetes to get past these problems! The DevOps team has decided to use the Kubernetes plugin in Jenkins to run our jobs as Kubernetes pods so that we no longer have to worry about not having enough resources. I'm counting on you to deploy the entire solution. I will attach the code for a simple Jenkins job as an asset named test_job_on_kubernetes.groovy.You can create and run a job with this code to ensure that the Kubernetes configuration was successful. Once the job runs in Jenkins, it will sleep for 60 seconds so that you can go to Kubernetes to verify that a pod was created and it's running the job.

You will have to ensure that Ingress is enabled so that we can access the Jenkins UI with a web browser. You also need to make sure that persistent storage is properly configured to avoid any data loss. Regarding the permissions that Jenkins will need to create Kubernetes pods, the security area sent them to me to ensure that we don't grant more permissions than we should - I will attach that file as well as an asset named executors_rbac_permissions.yaml. Finally, ensure that when you trigger a job in Jenkins, it runs the steps as a Kubernetes pod - I can't stress how important this is, because that's the main goal of this project!

- To use minikube and test if any service is working use: 
    minikube service <servicio> -n <namespace> --url 

- To create a Persistent volume it is important to follow this guide: 
    https://kubernetes.io/docs/tasks/configure-pod-container/configure-persistent-volume-storage/

- To make the pod claim the PV correctly you should give permissions to the /mnt/data directory inside minikube container.
    Basically go inside minikube: minikube ssh
    sudo chown -R 1000:1000 /mnt/data
    sudo chmod -R 775 /mnt/data


2. create service of type LoadBalancer to make the Jenkins server accessible through a web browser. You will find a button named "Web Server" on your workspace's top right - the Jenkins UI should load when you click on it.

3. provision and mount a Persistent Volume (PV) into the Jenkins pod to ensure that any future configurations such as plugin installations, job creation, and more are persisted across pod deletion.

4. configure RBAC permissions on a service account so that the Jenkins pod can create and delete pods each time a job starts or ends. These pods will be the executors that run the jobs in Jenkins as pods in Kubernetes.

5. To enable communication with the Jenkins executors that will run on k8s, you have to enable port 50000 as part of the ClusterIP Service where the Jenkins master service was defined.