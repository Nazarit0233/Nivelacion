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

## Negrita y cursiva
**Negrita**  
*Cursiva*  

## Listas
- Elemento 1
- Elemento 2
- Elemento 3

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
Editas archivos en el working directory (directorio de trabajo), preparas cambios con:
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

