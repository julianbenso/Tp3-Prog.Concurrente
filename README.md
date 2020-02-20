# Tp3-Prog.Concurrente
## Consigna:
1. Modelar el sistema de un procesador con dos núcleos, extendiendo la red de Petri de la figura 1. Verificar todas sus propiedades haciendo uso de la herramienta PIPE o Petrinator.
2. Hacer el diagrama de clases que modele el sistema.
3. Implementar un objeto Política que resuelva el conflicto entre las transiciones sensibilizadas que alimentan los buffers de los núcleos, manteniendo la carga de la CPU equitativa.
4. Hacer el diagrama de secuencia que muestre el disparo exitoso de una de las transiciones que alimenta a uno de los buffers de la CPU, mostrando el uso de la política.
5. Indicar la cantidad de hilos necesarios para la ejecución y justificar.
6. Modelar el sistema con objetos en Java.
7. Realizar las siguientes ejecuciones con 1000 tareas completadas (para cada caso):
  A) Ambos núcleos con el mismo tiempo de “service_rate”. 
  B) Un núcleo con el doble de tiempo de “service_rate” que el otro. 
  C) Un núcleo con el triple de tiempo de “service_rate” que el otro.

8) Verificar los resultados haciendo uso de un archivo de log, verificando el cumplimiento de los invariantes.
9) Debe hacer una clase Main que al correrla, inicie el programa.

## Entregar:
1) Un archivo de imagen con el diagrama de clases, en buena calidad.
2) Un archivo de imagen con el diagrama de secuencias, en buena calidad.
3) Un archivo de imagen con la red de Petri, en buena calidad.
4) El código fuente Java (proyecto) de la resolución del ejercicio.
5) Un informe (obligatorio) que documente lo realizado, los criterios adoptados y que explique los resultados obtenidos.




