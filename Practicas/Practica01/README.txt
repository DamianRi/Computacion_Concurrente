## Práctica 1
### Números primos y encuentra el mínimo

##### Integrantes:
	- Hérnandez Castro Alexis
	- Rivera González Damián

### Ejecución
Una vez situado en la carpeta de `Practica01` en la terminal se ingresará el siguiente comando para compilar
```
	$ mvn package
```

Para correr la clase principal se usa el siguiente comando
```
	$ java -jar target/practica01-1.0.jar
```

Nota: para esta práctica no se creo ninguna función `main` que forme parte de una ejecución importante

Para ejecutar únicamente las pruebas unitarias usar el comando:
```
	$ mvn test
```

Para ejecutar las pruebas de una clase especifica usar el siguiente comando:

```
	$ mvn -Dtests=AppTest test
```

### Tabla de comparaciones

Comparacion Ejerciocio 1:

Número de hilos || Aceleracion Teorica || Aceleracion Obtenida || Porcentaje de Codigo Paralelo||
_________________________________________________________________________________________________  

       1        ||         1           ||         1            ||               100%           ||
_________________________________________________________________________________________________  

       8        ||         2.3         ||        1.5-2         ||               66.6%          ||
_________________________________________________________________________________________________  

                ||                     ||                      ||                              ||


Comparacion Ejerciocio 2:

Número de hilos || Aceleracion Teorica || Aceleracion Obtenida || Porcentaje de Codigo Paralelo||
_________________________________________________________________________________________________  

        1       ||          1          ||           1          ||              100%            ||
_________________________________________________________________________________________________  

        4       ||          1.6        ||           1          ||              53.6%           ||
_________________________________________________________________________________________________  

                ||                     ||                      ||                              ||
