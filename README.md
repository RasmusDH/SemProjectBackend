[![Build Status](https://travis-ci.com/Peter-Rambeck/CA3BackEnd.svg?branch=master)](https://travis-ci.com/Peter-Rambeck/CA3BackEnd)

# InSession startcode

## Team
**Peter R Andersen**
* [Github](https://github.com/Peter-Rambeck)

**Jens Gelbek**
* [Github](https://github.com/jensgelbek)

**Rasmus Ditlev Hansen**
* [Github](https://github.com/RasmusDH)

**Tobias Zimmermann**
* [Github](https://github.com/tobias-z)

## Getting started
1. Change the `remote.server` to your domain in the pom.xml
2. Change env variable `connection_str` in the EMF_Creator
3. Insert that new env variable on your droplets docker-compose.yml file together with a new database name
4. Run these commands:
    - docker-compose down
    - docker-compose build
    - docker-compose up -d
    
5. Create the new database on your droplet
6. Change REMOTE_USER & REMOTE_PW in Travis for Tomcat deploy.
7. In SetupTestUsers. Change user & admin credentials to generate users in a startup database.   

## Usage:
In HttpUtil are two methods designed to easily 'Fetch' from different external servers.
1. Method: fetchData, Fetch data from one link/endpoint.
2. Method: fetchMany, Fetch data from many links/endpoints.

Rest: Roles allowed annotations: Change the annotations if you want to make an endpoint accessible for different user-roles.  


### Travis
In the .travis.yml there is a section `only` under branches, this is where you can decide which branches should be build on push.
