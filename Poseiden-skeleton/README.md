# Poseidon Capital Solutions

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

Il s'agit d'une **application Web de TEST utilisant une API Rest** développée en Java avec Spring Boot 3.  
Chaque route de l'api nécessite l'utilisation d'un JWT.

Cette application permet de gérer les utilisateurs de l'application et d'alimenter le système avec des entités de base qui serviront à générer des transactions financières.

## Pour commencer

Faire un fork du projet ou le cloner.

### Pré-requis

* Java				: jdk 17.0.7
* Spring Boot		: 3.1.0
* Maven				: 3.8.7
* Thymeleaf-extras-springsecurity6
* MySQL				: 8
* Liste totale		: voir le fichier "pom.xml"

### Lancer les tests unitaires

_Executez la commande_  ``mvn clean test``

### Créer un fichier JAR

_Executez la commande_  ``mvn clean package``

## Démarrer l'application

Copier le dossier "config" et le fichier "Poseidon-api-0.0.1-SNAPSHOT.jar" dans le même dossier.

_Executez la commande_  ``java -jar Poseidon-api-0.0.1-SNAPSHOT.jar``

Copier le dossier "config" et le fichier "Poseidon-0.0.1-SNAPSHOT.jar" dans le même dossier.

_Executez la commande_  ``java -jar Poseidon-0.0.1-SNAPSHOT.jar``

## Tester l'application

### Créer une base de données MySQL 8:
* [Base de données SQL](./doc/data.sql)

### Dans un navigateur internet:
* Entrer l'adresse: ``http://localhost:8081/home``

### Login:
* s'identifier:
	* entrer le nom d'utilisateur et le mot de passe (8 caractères, au moins: 1 majuscule, 1 minuscule, 1 chiffre et 1 symbole) + cliquer sur le bouton "Login"
* exemple:
	* entrer dans la zone de texte Username: ``user``
	* entrer dans la zone de texte Password: ``123456aA!``

### User Management:
* gestion des utilisateurs réservé à un utilisateur avec le rôle "ADMIN"
* exemple:
	* entrer dans la zone de texte Username: ``admin``
	* entrer dans la zone de texte Password: ``123456aA!``

### Menu accessible uniquement pour "User Management"

* **Menu User List:**
	* opérations CRUD

### Menus accessibles après "Login" ou "User Management"

* **Menu Bid List:**
	* opérations CRUD

* **Menu Curve Points:**
	* opérations CRUD

* **Menu Ratings:**
	* opérations CRUD

* **Menu Trade:**
	* opérations CRUD

* **Menu Rule:**
	* opérations CRUD

* **Bouton Logout:**
	* sortir de l'application

### Information:
* Après 1 minute sans action de l'utilisateur, il devra s'authentifier à nouveau
* Pour modifier ce paramètre mettez en commentaire la ligne "server.servlet.session.timeout=60s" dans le fichier "application.properties"

## Développée avec

* eclipse 4.28.00

## Versions

Dernière version: SNAPSHOT: 0.0.1

## Auteur

* **Philippe PERNET**  _alias_  [@Philiform](https://github.com/Philiform)

## License

Ce projet n'est pas sous licence
