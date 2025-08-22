# **Taller de nivelación**

## **Markdown**
Markdown es un lenguaje de marcado ligero, es decir, una forma sencilla de dar formato a un texto sin necesidad de usar programas complejos como Word, se utiliza mucho en entornos de programación, documentación técnica, páginas web, foros y GitHub porque:
- Es fácil de escribir: solo necesitas caracteres comunes como #, *, -.
- Es portable: el mismo archivo se puede abrir en cualquier editor de texto.
- Se convierte fácilmente a HTML, PDF, Word, etc.

## Títulos
\# Título 1
\## Título 2
\### Título 3

# titulo 1
## Título 2
### Título 3

## Escapado de caracteres especiales
Para escribir directamente caracteres especiales con markdown, existe el **escape** de caracteres con la barra invertida **\***, anteponiendola a un caracter especial le indicas a Markdown que no lo procese como formato.

\# No se interpretara como un titulo de nivel 1

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


## **Git**
1. ### ¿Qué es un repositorio en Git?
Un repositorio en Git es un espacio donde se almacena y gestiona el historial de versiones de un proyecto. Básicamente, es una carpeta que, además de contener tus archivos de trabajo (código, documentos, imágenes, etc.), incluye una carpeta oculta llamada .git/ que guarda toda la información sobre los cambios realizados, las ramas, los commits y las referencias.

#### ¿cómo se diferencia de un proyecto “normal”?
Se diferencia de un projecto normal al permitir: Historial de cambios, control de versiones, colaboracion, Ramas(braches) y sincronización remota.

Un repositorio Git es un proyecto con memoria e inteligencia colaborativa, mientras que un proyecto normal es solo una carpeta de archivos sin historial ni control.

2.  ### Working Directory (Directorio de trabajo)
Es la carpeta donde editas tus archivos normalmente (tu proyecto en el disco), aquí tienes la versión actual de los archivos, lista para modificar, crear o borrar, los cambios en esta área aún no están registrados por Git.
  
#### Staging Area (Índice / Área de preparación)
Es como una “sala de espera” donde colocas los cambios que quieres guardar en el próximo commit.
Aquí decides qué cambios incluir y cuáles no (puedes preparar unos archivos y dejar otros fuera).
   
#### Repository (Repositorio / .git)
Es la base de datos donde Git almacena todos los commits confirmados y el historial completo del proyecto, una vez que haces git commit, los cambios que estaban en el staging area pasan al repository y quedan registrados permanentemente, aquí es donde vive toda la “memoria” de tu proyecto.

3. ### ¿Cómo representa Git los cambios internamente?
Git internamente no guarda "archivos completos" cada vez que haces un commit, sino que usa una estructura de objetos, los cuatro objetos principales que maneja Git son: blob, tree, commit y tag.
  
#### Blob (Binary Large Object)
Representa el contenido de un archivo, no guarda el nombre del archivo, solo los bytes del contenido, Git calcula un hash SHA-1 (o SHA-256 en nuevas versiones) del contenido, y eso identifica al blob, si dos archivos distintos tienen el mismo contenido, Git guarda un único blob para ambos (ahorra espacio).
  
#### Tree
Representa un directorio (carpeta), contiene referencias a otros blobs (archivos) y a otros trees (subcarpetas), además de apuntar al blob, guarda el nombre del archivo y sus permisos.
   
#### Commit
Representa un punto en el tiempo del proyecto, un commit guarda:
El hash del árbol raíz (tree) que representa el proyecto.
El padre (o padres, si es un merge).
Autor, fecha y mensaje de commit.
  
#### Tag
Es una etiqueta que apunta a un commit específico, puede ser:
Ligera (lightweight): solo un puntero.
Anotada (annotated): incluye nombre del autor, fecha, mensaje y puede estar firmada.
Y los tags son solo "post-it" que se pegan en commits para marcar versiones importantes.

4. ### ¿Cómo se crea un commit?
Editas archivos en el *working directory* (directorio de trabajo), preparas cambios con:
git add (esto los mueve al staging area).
Confirmas cambios con:
git commit -m "Mensaje descriptivo" (En ese momento, Git crea un objeto commit en el repositorio.)
   
#### ¿qué información almacena un objeto Commit?
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
   
#### git pull
Hace lo mismo que git fetch, pero además ejecuta un merge (o rebase, si lo configuras) en tu rama actual, es decir, actualiza tu historial y trata de mezclar automáticamente los cambios remotos con tus cambios locales.

6.  ### ¿Qué es un branch?
En Git, un branch es simplemente un puntero (referencia) a un commit específico, no es una copia completa del proyecto ni un directorio aparte, sino un marcador que avanza automáticamente cada vez que haces un nuevo commit.
  
#### ¿Cómo gestiona Git los punteros a commits?
Cada commit tiene un identificador único (SHA-1 o SHA-256) y un puntero a su padre (commit anterior), esto forma una cadena de commits (una historia lineal o ramificada), una rama es solo un archivo en .git/refs/heads/ que guarda el hash del commit al que apunta, El puntero especial HEAD indica en qué rama (o commit) estás trabajando actualmente, normalmente HEAD apunta a una rama (por ejemplo, main), cuando haces un nuevo commit Git mueve la rama hacia adelante al nuevo commit y HEAD lo sigue.

7.  ### ¿Cómo se realiza un merge en Git?
Te posicionas en la rama que quieres actualizar: git checkout main(Ahora estás en main).
Fusionas otra rama en la actual: git merge feature(Esto intenta combinar los cambios de feature en main).
    
#### ¿Qué conflictos pueden surgir?
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

#### ¿Cómo se resuelven los conflictos?
Git te avisa qué archivos tienen conflictos (git status), abres esos archivos y eliges manualmente qué versión conservar:
- Solo la de main
- Solo la de feature
- Una combinación de ambas
Una vez resuelto el archivo, lo marcas como solucionado: git add archivo.txt
Finalmente, confirmas el merge: git commit (Git crea un commit de merge con los cambios ya resueltos).

8. ### ¿Cómo funciona el área de staging (git add)?
El staging area (o índice) es como una sala de espera donde decides qué cambios van a formar parte del próximo commit, cuando editas un archivo en el *working directory* (tu carpeta normal), Git detecta el cambio, pero aún no está listo para ser guardado.
Cuando haces: git add ese cambio pasa al staging area.
    *En este momento Git guarda una "foto" del archivo en ese estado específico.*
    *Así puedes preparar selectivamente qué cambios quieres guardar y cuáles no.*
Finalmente, al hacer: git commit -m
    *Git toma solo lo que estaba en el staging area y lo guarda en el repositorio como un nuevo commit.*

#### ¿Qué pasa si omito el git add?
Si editas un archivo y haces directamente: git commit
Entonces NO se guardan tus cambios nuevos, solo se confirma lo que ya estaba en el staging area (o nada, si estaba vacío).
Eso significa que:
Tus cambios seguirán en el *working directory* (no se pierden).
Pero no estarán registrados en el historial de Git.

9. ### ¿Qué es .gitignore?
Es un archivo de texto (normalmente en la raíz del proyecto) donde se escriben patrones de nombres de archivos o directorios que Git debe ignorar, Sirve para evitar que se suban al repositorio archivos innecesarios, temporales o sensibles.

#### ¿Cómo influye en el seguimiento de archivos?
Git NO rastrea archivos que coinciden con lo definido en .gitignore, eso significa que no aparecen como “changes” cuando haces git status, si un archivo ya fue rastreado antes de ponerlo en .gitignore, Git seguirá controlándolo, en ese caso, hay que sacarlo primero del seguimiento:
git rm --cached y luego sí Git lo ignorará.
*.gitignore solo afecta a los archivos sin seguimiento (“untracked”), no a los que ya están versionados.*

10. ### ¿Cuál es la diferencia entre un “commit amend” (--amend) y un nuevo commit?
### Nuevo commit
Cuando usas: git commit -m
Git crea un objeto commit totalmente nuevo, Con su propio hash único (SHA), apuntando al commit anterior como su padre con el mensaje y cambios que preparaste en el staging area.

#### Commit amend (git commit --amend)
Cuando usas: git commit --amend
Git no crea un commit adicional, sino que reemplaza el último commit con uno nuevo, permite modificar el mensaje del último commit O añadir/quitar cambios que olvidaste (con git add antes de --amend).

*Nuevo commit → agrega un nodo más al historial.*
*Amend → reescribe el último commit (cambia su hash, mensaje y/o contenido).*

11. ### ¿Qué es git stash?
Es un comando que guarda temporalmente los cambios de tu working directory y del staging area sin hacer un commit, mueve esos cambios a un área especial llamada stash stack (pila de guardados temporales), deja tu rama limpia, como si acabaras de hacer git checkout sin cambios, más tarde puedes recuperar esos cambios y seguir trabajando en ellos.

#### Uso básico
    a. Guardar cambios en el stash: git stash
Guarda todos los cambios (tracked files) y limpia tu directorio.
*También puedes darle un nombre: git stash push -m "Trabajo en login"*

    b. Ver la lista de stashes guardados: git stash list

    c. Recuperar los cambios
Para aplicar y mantener el stash en la lista: git stash apply stash@{0}
Para aplicar y eliminarlo de la lista: git stash pop

    d. Eliminar stashes: git stash drop stash@{0}
O limpiar todos: git stash clear

#### Escenarios útiles de git stash
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

12. ### ¿Qué mecanismos ofrece Git para deshacer cambios (por ejemplo, git reset, git revert, git checkout)?
Git ofrece varios mecanismos dependiendo de qué quieres deshacer y cómo:

#### git checkout (cambiar el contenido del working directory)
Sirve para descartar cambios en archivos que aún no has agregado al staging area, también se usa para moverte entre ramas o commits (aunque en versiones nuevas se prefiere git switch para ramas).

#### git reset (mueve HEAD y opcionalmente borra cambios)
Se usa para reescribir el historial local y mover el puntero de la rama (HEAD), Tiene tres modos principales:
- --soft → mueve HEAD, pero mantiene cambios en staging. git reset --soft HEAD~1
*Deshace el último commit, pero los cambios siguen listos para volver a commitear.*

- --mixed (por defecto) → mueve HEAD y saca los cambios del staging (vuelven al working directory). git reset HEAD~1

- --hard peligroso, elimina el commit y los cambios del todo. git reset --hard HEAD~1

#### git revert (crear un commit que revierte otro)
En lugar de borrar commits, crea un nuevo commit que deshace los cambios de uno anterior.
Se usa cuando quieres deshacer algo en una rama compartida con otros (seguro en remoto).

13. ###  ¿Cómo funciona la configuración de remotos (origin, upstream)?
#### ¿Qué es un remoto en Git?
Un remoto es simplemente un alias (nombre corto) que apunta a la URL de un repositorio en línea, cuando clonas un repo de GitHub, Git crea automáticamente un remoto llamado origin, que apunta al repo del que clonaste, puedes agregar más remotos con otros nombres, por ejemplo upstream, para seguir el repositorio original de un proyecto que has forked.

#### ¿Qué es origin?
Es el nombre por defecto que Git le da al remoto desde el cual clonaste, normalmente apunta a tu propio fork del proyecto.

#### ¿Qué es upstream?
Es un remoto que tú agregas manualmente para apuntar al repositorio original del cual hiciste el fork, así puedes traer actualizaciones del proyecto principal.

#### ¿qué comandos uso para gestión de forks?
- Haces un fork en GitHub del proyecto original, tu copia está en: https://github.com/tu-usuario/repo.git
- clonas tu fork (esto crea el remoto origin): git clone https://github.com/tu-usuario/repo.git cd repo
- agregas el remoto upstream (el repo original): git remote add upstream https://github.com/proyecto-original/repo.git
- Compruebas los remotos configurados: git remote -v
- Mantener tu fork actualizado:
    - Traes cambios del repo original: git fetch upstream
    - Actualizas tu rama main con los cambios del upstream: git pull upstream main
    - Subir tus cambios a tu fork: git push origin main
    - Crear un Pull Request en GitHub desde tu fork → al repo original.

14. ### ¿Cómo puedo inspeccionar el historial de commits (por ejemplo, git log, git diff, git show)?
En Git tienes varias herramientas para inspeccionar el historial de commits y los cambios que se han hecho,  los más importantes:

#### git log → ver el historial de commits
Muestra la lista de commits en orden cronológico inverso (los más recientes primero): git log
- Mostrar en una sola línea: git log --oneline
- Ver ramas como un gráfico: git log --oneline --graph --all
- Filtrar por autor: git log --author="Juan"
- Filtrar por archivo específico: git log -- archivo.txt

#### git show → ver un commit en detalle
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

#### git diff → comparar cambios
Sirve para ver diferencias entre commits, ramas o tu directorio actual.
- Ver cambios no añadidos (working directory): git diff
- Ver cambios ya añadidos al staging area: git diff --staged
- Comparar dos commits: git diff <commit1> <commit2>
- Comparar ramas: git diff main feature

*git log → historial de commits (mensajes, autores, fechas).*
*git show → detalles de un commit específico.*
*git diff → diferencias de contenido entre versiones (commits, ramas o staging).*

## **Programación**
15. ### ¿Cuáles son los tipos de datos primitivos en Java?
En Java existen 8 tipos primitivos, que se pueden separar en:
   
   #### Numéricos enteros
   - byte → entero pequeño (8 bits, rango: -128 a 127).
   - short → entero corto (16 bits, rango: -32,768 a 32,767).
   - int → entero estándar (32 bits, rango: -2,147,483,648 a 2,147,483,647).
   - long → entero largo (64 bits, rango: muy grande, aprox. ±9×10^18).
   
   #### Numéricos con decimales
   - float → número en coma flotante de precisión simple (32 bits).
   - double → número en coma flotante de doble precisión (64 bits).
   
   #### Lógico
   - boolean → almacena true o false (1 bit conceptual, pero ocupa 1 byte en memoria).
   
   #### char → un carácter Unicode (16 bits).
   - char → un carácter Unicode (16 bits).

16. ### ¿Cómo funcionan las estructuras de control de flujo como if, else, switch y bucles en Java?
En Java, las estructuras de control de flujo permiten decidir qué instrucciones ejecutar y cuántas veces.

#### Condicionales (if, else if, else)
Sirven para tomar decisiones en el programa según una condición booleana.
    if (condicion) {
    // Se ejecuta si la condición es true
    } else if (otraCondicion) {
    // Se ejecuta si la primera es false y esta es true
    } else {
    // Se ejecuta si ninguna condición anterior se cumple
    }

#### switch
Permite evaluar una expresión y ejecutar diferentes bloques según el valor (Suele usarse con int, char, String, enum).
    switch (variable) {
    case valor1:
        // código
        break;
    case valor2:
        // código
        break;
    default:
        // si no coincide con ningún caso
    }

#### Bucles (repetición)
   ##### while
   Ejecuta el bloque mientras la condición sea true.
   int i = 0;
   while (i < 5) {
   System.out.println("i = " + i);
   i++;
   }

   ##### do...while
   Similar a while, pero siempre ejecuta al menos una vez.
   int j = 0;
   do {
   System.out.println("j = " + j);
   j++;
   } while (j < 5);

   ##### for
   Bucle con inicialización, condición y actualización en una sola línea.
   for (int k = 0; k < 5; k++) {
   System.out.println("k = " + k);
   }

   ##### for-each (mejorado)
   Sirve para recorrer arreglos o colecciones de manera sencilla.
   String[] frutas = {"Manzana", "Banana", "Pera"};
   for (String fruta : frutas) {
   System.out.println(fruta);
   }
17. ###  ¿Por qué es importante usar nombres significativos para variables y métodos?
#### Razones por las que es importante:
   ##### Claridad y legibilidad del código
   Un nombre como edad es mucho más claro que x, facilita que otros (o tú mismo en el futuro) entiendan rápidamente qué hace el programa.
   int edad = 25;   // Claro
   int x = 25;      // Confuso

   ##### Mantenimiento más sencillo
   El software se modifica con el tiempo, si los nombres son descriptivos, es más fácil encontrar y cambiar lo necesario.
   // Malo
   double a(double b, double c) {
   return b * c;
   }

   // Mejor
   double calcularAreaRectangulo(double base, double altura) {
   return base * altura;
   }

   ##### Evita errores
   Nombres claros reducen confusiones y malas interpretaciones, si una variable se llama saldoDisponible, es menos probable que la uses para algo distinto.

   ##### Colaboración en equipo
   En proyectos de equipo, el código es leído por varias personas, nombres significativos hacen que el código sea auto-documentado, reduciendo la necesidad de comentarios excesivos.

   ##### Buenas prácticas de estilo y convenciones
   En Java existen convenciones de nombres:
   - Variables y métodos → camelCase (numeroClientes, calcularPromedio()).
   - Clases → PascalCase (Estudiante, CuentaBancaria).
   - Constantes → MAYÚSCULAS_CON_GUIONES (PI, MAX_VALUE).

18. ### ¿Qué es la Programación Orientada a Objetos (POO)?
La Programación Orientada a Objetos (POO) es un paradigma de programación que organiza el código en torno a objetos, los cuales combinan datos y comportamientos en una misma entidad.

#### Concepto básico
En lugar de ver el programa como un conjunto de funciones sueltas, en la POO pensamos en objetos que representan cosas del mundo real o conceptos abstractos.
- Clase → Es el "molde" o plantilla (define atributos y métodos).
- Objeto → Es una instancia de una clase (algo concreto creado a partir del molde).

19. ### ¿Cuáles son los cuatro pilares de la Programación Orientada a Objetos?
##### Encapsulamiento
   - Agrupar datos (atributos) y comportamientos (métodos) en una clase, proteger los datos internos usando modificadores de acceso (private, public).

##### Abstracción
   - Representar solo los aspectos esenciales de un objeto, Ejemplo: una clase Auto puede tener acelerar() sin mostrar cómo funciona el motor internamente.

##### Herencia
   - Permite que una clase herede atributos y métodos de otra, Ejemplo: Estudiante puede heredar de Persona.

##### Polimorfismo
   - Un mismo método puede tener diferentes implementaciones según el contexto, Ejemplo: dibujar() se comporta diferente en un Círculo y en un Cuadrado.

20. ###  ¿Qué es la herencia en POO?
Es un mecanismo que permite que una clase (hija o subclase) adquiera los atributos y métodos de otra clase (padre o superclase), es una forma de reutilizar código ya existente, modela relaciones del tipo “es un” (IS-A), la clase hija puede heredar, extender o sobrescribir el comportamiento de la clase padre.

#### ¿cómo se utiliza en Java?
Ejemplo: Estudiante puede heredar de Persona.
// Clase padre (superclase)
class Persona {
    String nombre;
    int edad;

    void presentarse() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}

// Clase hija (subclase) hereda de Persona
class Estudiante extends Persona {
    String universidad;

    void estudiar() {
        System.out.println(nombre + " está estudiando en " + universidad);
    }
}

21. ### ¿Qué son los modificadores de acceso?
los modificadores de acceso son palabras clave que controlan qué tan visible es una clase, atributo, constructor o método desde otras partes del programa, sirven para aplicar el principio de encapsulamiento en la Programación Orientada a Objetos (POO).

#### public
Accesible desde cualquier parte (misma clase, mismo paquete, subclases y otras clases externas), Se aplica a clases, métodos, variables.

#### protected
Accesible dentro del mismo paquete y también por subclases (aunque estén en otros paquetes), Se aplica a métodos, variables, constructores.

#### default (package-private) (sin escribir nada)
Accesible solo dentro del mismo paquete, Se aplica a clases, métodos, variables.

#### private
Accesible solo dentro de la misma clase. Nadie más puede verlo directamente, Se aplica a métodos, variables, constructores.

*public → visible en todos lados.*
*protected → visible en el paquete y subclases.*
*default (sin palabra) → visible solo en el mismo paquete.*
*private → visible solo dentro de la clase.*

### ¿cuáles son los más comunes en Java?

#### public
Muy usado en clases, métodos y constructores que deben ser accesibles desde cualquier parte del programa.

#### private
El más usado para atributos (variables de instancia), porque se busca aplicar encapsulamiento, normalmente se accede a ellos mediante getters y setters.

#### protected
Se usa mucho en herencia: cuando quieres que las subclases tengan acceso a un atributo/método, pero que no sea completamente público.

#### default (package-private)
Es el menos usado directamente (porque se aplica cuando NO escribes ningún modificador), se utiliza cuando quieres que algo sea visible solo dentro del mismo paquete, pero no fuera de él.

22. ### ¿Qué es una variable de entorno?
Es una clave–valor que vive en el entorno del sistema (Windows, Linux, macOS), sirve para configurar parámetros globales sin necesidad de modificarlos directamente en el código fuente.

En Windows:
set JAVA_HOME=C:\Program Files\Java\jdk-21

En Linux/Mac:
export JAVA_HOME=/usr/lib/jvm/java-21

### ¿Por qué son importantes en Java?
#### Configuración del JDK y JVM
Variables como JAVA_HOME le dicen al sistema dónde está instalado Java, PATH permite ejecutar java o javac desde cualquier carpeta.

#### Flexibilidad en despliegue
En vez de "quemar" rutas, contraseñas o claves en el código, se definen como variables de entorno.

#### Seguridad
Permiten guardar credenciales (API keys, contraseñas, tokens) fuera del código, así no se exponen en repositorios (GitHub, GitLab).

#### Portabilidad
Un mismo programa puede correr en distintos entornos (desarrollo, pruebas, producción) solo cambiando las variables, sin tocar el código.