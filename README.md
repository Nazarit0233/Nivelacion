# Taller de nivelación

## Markdown
Markdown es un lenguaje de marcado ligero, es decir, una forma sencilla de dar formato a un texto sin necesidad de usar programas complejos como Word, se utiliza mucho en entornos de programación, documentación técnica, páginas web, foros y GitHub porque:
- Es fácil de escribir: solo necesitas caracteres comunes como #, *, -.
- Es portable: el mismo archivo se puede abrir en cualquier editor de texto.
- Se convierte fácilmente a HTML, PDF, Word, etc.

## Títulos
-# Título 1
-## Título 2
-### Título 3

# titulo 1
## Título 2
### Título 3

## Negrita y cursiva
**Negrita**  
*Cursiva*  

## Listas
- Elemento 1
- Elemento 2
- Elemento 3

1. Elemento 1
2. Elemento 2
3. Elemento 3

## Enlaces
[Google](https://www.google.com)

## Imágenes
![Texto alternativo](https://a-static.besthdwallpaper.com/jinx-in-arcane-netflix-series-league-of-legends-lol-wallpaper-2880x1800-92560_8.jpg)


## Git
1. ### ¿Qué es un repositorio en Git?
Un repositorio en Git es un espacio donde se almacena y gestiona el historial de versiones de un proyecto. Básicamente, es una carpeta que, además de contener tus archivos de trabajo (código, documentos, imágenes, etc.), incluye una carpeta oculta llamada .git/ que guarda toda la información sobre los cambios realizados, las ramas, los commits y las referencias.

### ¿cómo se diferencia de un proyecto “normal”?
Se diferencia de un projecto normal al permitir: Historial de cambios, control de versiones, colaboracion, Ramas(braches) y sincronización remota.

Un repositorio Git es un proyecto con memoria e inteligencia colaborativa, mientras que un proyecto normal es solo una carpeta de archivos sin historial ni control.

2.  ### Working Directory (Directorio de trabajo)
Es la carpeta donde editas tus archivos normalmente (tu proyecto en el disco), aquí tienes la versión actual de los archivos, lista para modificar, crear o borrar, los cambios en esta área aún no están registrados por Git.
  
### Staging Area (Índice / Área de preparación)
Es como una “sala de espera” donde colocas los cambios que quieres guardar en el próximo commit.
Aquí decides qué cambios incluir y cuáles no (puedes preparar unos archivos y dejar otros fuera).
   
### Repository (Repositorio / .git)
Es la base de datos donde Git almacena todos los commits confirmados y el historial completo del proyecto, una vez que haces git commit, los cambios que estaban en el staging area pasan al repository y quedan registrados permanentemente, aquí es donde vive toda la “memoria” de tu proyecto.

3. ### ¿Cómo representa Git los cambios internamente?
Git internamente no guarda "archivos completos" cada vez que haces un commit, sino que usa una estructura de objetos, los cuatro objetos principales que maneja Git son: blob, tree, commit y tag.
  
### Blob (Binary Large Object)
Representa el contenido de un archivo, no guarda el nombre del archivo, solo los bytes del contenido, Git calcula un hash SHA-1 (o SHA-256 en nuevas versiones) del contenido, y eso identifica al blob, si dos archivos distintos tienen el mismo contenido, Git guarda un único blob para ambos (ahorra espacio).
  
### Tree
Representa un directorio (carpeta), contiene referencias a otros blobs (archivos) y a otros trees (subcarpetas), además de apuntar al blob, guarda el nombre del archivo y sus permisos.
   
### Commit
Representa un punto en el tiempo del proyecto, un commit guarda:
El hash del árbol raíz (tree) que representa el proyecto.
El padre (o padres, si es un merge).
Autor, fecha y mensaje de commit.
  
### Tag
Es una etiqueta que apunta a un commit específico, puede ser:
Ligera (lightweight): solo un puntero.
Anotada (annotated): incluye nombre del autor, fecha, mensaje y puede estar firmada.
Y los tags son solo "post-it" que se pegan en commits para marcar versiones importantes.

4. ### ¿Cómo se crea un commit?
Editas archivos en el *working directory* (directorio de trabajo), preparas cambios con:
git add (esto los mueve al staging area).
Confirmas cambios con:
git commit -m "Mensaje descriptivo" (En ese momento, Git crea un objeto commit en el repositorio.)
   
### ¿qué información almacena un objeto Commit?
Un commit en Git no es solo “una copia de los archivos”, sino un objeto con metadatos muy específicos, contiene:
- Referencia al árbol raíz (tree): El commit guarda el hash del objeto tree, que representa la estructura completa de directorios y blobs (archivos) en ese momento.
- Padres (parents): El commit guarda el hash de uno (o varios) commits anteriores.(*Normalmente tiene 1 padre, pero en caso de un merge puede tener 2 o más padres*).
- Autor: Nombre y correo de la persona que escribió los cambios.
- Committer: Nombre y correo de quien realmente aplicó el commit (a veces distinto del autor, por ejemplo en parches).
- Fecha y hora: Cuándo se creó y aplicó el commit.
- Mensaje de commit: Texto descriptivo que explica qué cambios se hicieron.

5.  ### git fetch
Descarga los nuevos commits del repositorio remoto → pero NO los mezcla con tu rama actual, actualiza las referencias remotas (por ejemplo origin/main), pero deja tu rama tal como estaba, Es seguro: solo actualiza tu copia local del historial remoto, sin tocar tu trabajo.
git fetch → solo baja los cambios, te deja decidir qué hacer con ellos.
   
### git pull
Hace lo mismo que git fetch, pero además ejecuta un merge (o rebase, si lo configuras) en tu rama actual, es decir, actualiza tu historial y trata de mezclar automáticamente los cambios remotos con tus cambios locales.

6.  ### ¿Qué es un branch?
En Git, un branch es simplemente un puntero (referencia) a un commit específico, no es una copia completa del proyecto ni un directorio aparte, sino un marcador que avanza automáticamente cada vez que haces un nuevo commit.
  
### ¿Cómo gestiona Git los punteros a commits?
Cada commit tiene un identificador único (SHA-1 o SHA-256) y un puntero a su padre (commit anterior), esto forma una cadena de commits (una historia lineal o ramificada), una rama es solo un archivo en .git/refs/heads/ que guarda el hash del commit al que apunta, El puntero especial HEAD indica en qué rama (o commit) estás trabajando actualmente, normalmente HEAD apunta a una rama (por ejemplo, main), cuando haces un nuevo commit Git mueve la rama hacia adelante al nuevo commit y HEAD lo sigue.

7.  ### ¿Cómo se realiza un merge en Git?
Te posicionas en la rama que quieres actualizar: git checkout main(Ahora estás en main).
Fusionas otra rama en la actual: git merge feature(Esto intenta combinar los cambios de feature en main).
    
### ¿Qué conflictos pueden surgir?
Un conflicto aparece cuando:
- El mismo archivo fue modificado en ambas ramas, y los cambios afectan la misma línea o zonas muy cercanas del archivo, ejemplo:
    En main editaste linea 10.
    En feature también editaste la linea 10 pero de otra forma.
*Git no sabe cuál conservar, entonces marca un conflicto*
<<<<<<< HEAD
código desde rama main
=======
código desde rama feature
>>>>>>> feature

### ¿Cómo se resuelven los conflictos?
Git te avisa qué archivos tienen conflictos (git status), abres esos archivos y eliges manualmente qué versión conservar:
- Solo la de main
- Solo la de feature
- Una combinación de ambas
Una vez resuelto el archivo, lo marcas como solucionado: git add archivo.txt
Finalmente, confirmas el merge: git commit (Git crea un commit de merge con los cambios ya resueltos).

8. ## ¿Cómo funciona el área de staging (git add)?
El staging area (o índice) es como una sala de espera donde decides qué cambios van a formar parte del próximo commit, cuando editas un archivo en el *working directory* (tu carpeta normal), Git detecta el cambio, pero aún no está listo para ser guardado.
Cuando haces: git add ese cambio pasa al staging area.
    *En este momento Git guarda una "foto" del archivo en ese estado específico.*
    *Así puedes preparar selectivamente qué cambios quieres guardar y cuáles no.*
Finalmente, al hacer: git commit -m
    *Git toma solo lo que estaba en el staging area y lo guarda en el repositorio como un nuevo commit.*

## ¿Qué pasa si omito el git add?
Si editas un archivo y haces directamente: git commit
Entonces NO se guardan tus cambios nuevos, solo se confirma lo que ya estaba en el staging area (o nada, si estaba vacío).
Eso significa que:
Tus cambios seguirán en el *working directory* (no se pierden).
Pero no estarán registrados en el historial de Git.

9. ## ¿Qué es .gitignore?
Es un archivo de texto (normalmente en la raíz del proyecto) donde se escriben patrones de nombres de archivos o directorios que Git debe ignorar, Sirve para evitar que se suban al repositorio archivos innecesarios, temporales o sensibles.

## ¿Cómo influye en el seguimiento de archivos?
Git NO rastrea archivos que coinciden con lo definido en .gitignore, eso significa que no aparecen como “changes” cuando haces git status, si un archivo ya fue rastreado antes de ponerlo en .gitignore, Git seguirá controlándolo, en ese caso, hay que sacarlo primero del seguimiento:
git rm --cached y luego sí Git lo ignorará.
*.gitignore solo afecta a los archivos sin seguimiento (“untracked”), no a los que ya están versionados.*

10. ## ¿Cuál es la diferencia entre un “commit amend” (--amend) y un nuevo commit?
### Nuevo commit
Cuando usas: git commit -m
Git crea un objeto commit totalmente nuevo, Con su propio hash único (SHA), apuntando al commit anterior como su padre con el mensaje y cambios que preparaste en el staging area.

### Commit amend (git commit --amend)
Cuando usas: git commit --amend
Git no crea un commit adicional, sino que reemplaza el último commit con uno nuevo, permite modificar el mensaje del último commit O añadir/quitar cambios que olvidaste (con git add antes de --amend).

*Nuevo commit → agrega un nodo más al historial.*
*Amend → reescribe el último commit (cambia su hash, mensaje y/o contenido).*

11. ## ¿Qué es git stash?
Es un comando que guarda temporalmente los cambios de tu working directory y del staging area sin hacer un commit, mueve esos cambios a un área especial llamada stash stack (pila de guardados temporales), deja tu rama limpia, como si acabaras de hacer git checkout sin cambios, más tarde puedes recuperar esos cambios y seguir trabajando en ellos.

### Uso básico
    a. Guardar cambios en el stash: git stash
Guarda todos los cambios (tracked files) y limpia tu directorio.
*También puedes darle un nombre: git stash push -m "Trabajo en login"*

    b. Ver la lista de stashes guardados: git stash list

    c. Recuperar los cambios
Para aplicar y mantener el stash en la lista: git stash apply stash@{0}
Para aplicar y eliminarlo de la lista: git stash pop

    d. Eliminar stashes: git stash drop stash@{0}
O limpiar todos: git stash clear

### Escenarios útiles de git stash
    a. Cambiar de rama sin perder cambios
Estás en main editando, pero necesitas cambiar a feature para revisar algo, Guardas cambios:
- git stash
- git checkout feature
Luego vuelves a main y recuperas:
- git checkout main
- git stash pop

    b. Actualizar tu rama (git pull) sin conflictos
Si tienes cambios sin commit y haces git pull, puede dar error, Solución:
- git stash
- git pull
- git stash pop

    c. Probar algo rápido sin arruinar tu trabajo actual
Guardas tu progreso, haces cambios experimentales, y si no funcionan, simplemente descartas el stash.

12. ## ¿Qué mecanismos ofrece Git para deshacer cambios (por ejemplo, git reset, git revert, git checkout)?
Git ofrece varios mecanismos dependiendo de qué quieres deshacer y cómo:

### git checkout (cambiar el contenido del working directory)
Sirve para descartar cambios en archivos que aún no has agregado al staging area, también se usa para moverte entre ramas o commits (aunque en versiones nuevas se prefiere git switch para ramas).

### git reset (mueve HEAD y opcionalmente borra cambios)
Se usa para reescribir el historial local y mover el puntero de la rama (HEAD), Tiene tres modos principales:
- --soft → mueve HEAD, pero mantiene cambios en staging. git reset --soft HEAD~1
*Deshace el último commit, pero los cambios siguen listos para volver a commitear.*

- --mixed (por defecto) → mueve HEAD y saca los cambios del staging (vuelven al working directory). git reset HEAD~1

- --hard peligroso, elimina el commit y los cambios del todo. git reset --hard HEAD~1

### git revert (crear un commit que revierte otro)
En lugar de borrar commits, crea un nuevo commit que deshace los cambios de uno anterior.
Se usa cuando quieres deshacer algo en una rama compartida con otros (seguro en remoto).

13. ##  ¿Cómo funciona la configuración de remotos (origin, upstream)?
### ¿Qué es un remoto en Git?
Un remoto es simplemente un alias (nombre corto) que apunta a la URL de un repositorio en línea, cuando clonas un repo de GitHub, Git crea automáticamente un remoto llamado origin, que apunta al repo del que clonaste, puedes agregar más remotos con otros nombres, por ejemplo upstream, para seguir el repositorio original de un proyecto que has forked.

### ¿Qué es origin?
Es el nombre por defecto que Git le da al remoto desde el cual clonaste, normalmente apunta a tu propio fork del proyecto.

### ¿Qué es upstream?
Es un remoto que tú agregas manualmente para apuntar al repositorio original del cual hiciste el fork, así puedes traer actualizaciones del proyecto principal.

## ¿qué comandos uso para gestión de forks?
- Haces un fork en GitHub del proyecto original, tu copia está en: https://github.com/tu-usuario/repo.git
- clonas tu fork (esto crea el remoto origin): git clone https://github.com/tu-usuario/repo.git cd repo
- agregas el remoto upstream (el repo original): git remote add upstream https://github.com/proyecto-original/repo.git
- Compruebas los remotos configurados: git remote -v
- Mantener tu fork actualizado:
    - Traes cambios del repo original: git fetch upstream
    - Actualizas tu rama main con los cambios del upstream: git pull upstream main
    - Subir tus cambios a tu fork: git push origin main
    - Crear un Pull Request en GitHub desde tu fork → al repo original.

14. ## ¿Cómo puedo inspeccionar el historial de commits (por ejemplo, git log, git diff, git show)?
En Git tienes varias herramientas para inspeccionar el historial de commits y los cambios que se han hecho,  los más importantes:

### git log → ver el historial de commits
Muestra la lista de commits en orden cronológico inverso (los más recientes primero): git log
- Mostrar en una sola línea: git log --oneline
- Ver ramas como un gráfico: git log --oneline --graph --all
- Filtrar por autor: git log --author="Juan"
- Filtrar por archivo específico: git log -- archivo.txt

### git show → ver un commit en detalle
Muestra la información de un commit específico (autor, fecha, mensaje y diferencias de código): git show <commit_hash>
    Ejemplo: *git show a1b2c3*
    *commit a1b2c3d4e5f6*
*Author: Juan Pérez <juan@example.com>*
*Date:   Tue Aug 20 10:15:42 2024 -0500*

    *Corrijo bug en el login*

*diff --git a/app.js b/app.js*
*--- a/app.js*
*+++ b/app.js*
*@@ -10,6 +10,7 @@*
*-  return false;*
*+  return true;*

### git diff → comparar cambios
Sirve para ver diferencias entre commits, ramas o tu directorio actual.
- Ver cambios no añadidos (working directory): git diff
- Ver cambios ya añadidos al staging area: git diff --staged
- Comparar dos commits: git diff <commit1> <commit2>
- Comparar ramas: git diff main feature

*git log → historial de commits (mensajes, autores, fechas).*
*git show → detalles de un commit específico.*
*git diff → diferencias de contenido entre versiones (commits, ramas o staging).*
