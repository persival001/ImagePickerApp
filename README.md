# ImagePickerApp

ImagePickerApp est une application Android simple développée avec Jetpack Compose, permettant aux utilisateurs de sélectionner une image depuis leur galerie ou de prendre une photo avec l'appareil photo du téléphone.

## Fonctionnalités

- Sélectionner une image depuis la galerie.
- Prendre une photo avec l'appareil photo du téléphone.
- Afficher l'image sélectionnée ou prise dans l'interface utilisateur.

## Technologies Utilisées

- Kotlin
- Jetpack Compose
- Android Camera API
- Android Content Provider
- Coil pour le chargement d'images

## Permissions Requises

L'application nécessite les permissions suivantes :

- `CAMERA` : Nécessaire pour accéder à l'appareil photo du téléphone.
- `WRITE_EXTERNAL_STORAGE` : Nécessaire pour sauvegarder les images prises avec l'appareil photo dans le stockage externe du téléphone. (Note : Requise pour les versions d'Android inférieures à 10)
- `ACCESS_MEDIA_LOCATION` : Nécessaire pour accéder à l'emplacement des médias sur Android 10 et versions ultérieures.

## Installation

Pour exécuter ce projet, clonez ce dépôt et ouvrez-le avec Android Studio. Assurez-vous que vous avez configuré Android Studio avec le SDK Android approprié. Compilez et exécutez l'application sur un appareil ou un émulateur supportant au minimum Android SDK version 21 (Lollipop).

```bash
git clone https://github.com/persival001/ImagePickerApp.git
```

## Configuration
Assurez-vous d'avoir configuré un FileProvider dans votre fichier AndroidManifest.xml pour partager des fichiers entre votre application et l'appareil photo. Vous devrez également définir les chemins d'accès pour les fichiers partagés dans un fichier XML dans le dossier res/xml.

## Contribution
Les contributions sont les bienvenues. Pour contribuer, veuillez ouvrir une issue pour discuter de ce que vous aimeriez changer ou soumettre directement une pull request.
