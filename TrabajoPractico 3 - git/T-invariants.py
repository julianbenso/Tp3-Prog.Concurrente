import re
import numpy as np
import datetime




print ("Fecha de ejecucion: "  + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))

string_pattern = "(.*)T0(.*?)(?:T1(.*?)(?:T6(.*?)(?:T7(.*?)T5|T5(.*?)T7)|T5(.*?)T6(.*?)T7|T3(.*?)T4(.*?)T6(.*?)T7(.*?)T2)|T8(.*?)(?:T9(.*?)(?:T10(.*?)T12|T12(.*?)T10)|T12(.*?)T9(.*?)T10|T13(.*?)T14(.*?)T9(.*?)T10(.*?)T11))(.*)"
pattern = re.compile(string_pattern)



transitions_file = open("out/transitions.txt", "r").read()

no_of_matches = 0

resultado_de_transiciones = transitions_file


while True:
    nuevo_resultado = ""
    match = pattern.match(resultado_de_transiciones)
    if not match:
        break
    for group in match.groups():
        if group is not None: nuevo_resultado += group
    resultado_de_transiciones = nuevo_resultado
    no_of_matches += 1





print ("El resultado de reemplazar los grupos es: " + resultado_de_transiciones + "\n")
print ("La cantidad de invariantes eliminadas fue: " + str(no_of_matches))