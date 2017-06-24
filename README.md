# ShlagOverflow 

## Lancement du Back

Dans la console, se rendre dans le dossier racine du projet `/`

Executer la commande :  `java -jar shlagoverflow-0.0.1-SNAPSHOT.jar`

L'indexation du dataset peut prendre quelques minutes. 
En attendant, il faut lancer le client front. 
## Lancement du Front

Vous trouverez ci dessous la procédure pour lancer l'application front en local. 

### Dépendances

Pour lancer le client front, vous aurez besoin de Nodejs.
(NodeJs > 6.9.* et npm > 3.0)

Lien pour l'installation : https://nodejs.org/en/

Si vous utilisé Windows, pensé à redémarrer après l'installation de Nodejs

Ensuite, installer la dépendance Angular CLI :
`npm install -g @angular/cli`

### Lancement
- Se rendre dans le dossier `/front/shlag-front`
- Executez `npm install`
- Executez `npm run start`
- Accedez à l'application : [http://localhost:4200/](http://localhost:4200/)

### Alternative

Si vous ne pouvez pas utiliser Npm, 
vous pouvez toujours copier les sources présentes dans le dossier `front/shlag-front/dist`
sur un serveur web

