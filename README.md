# QT
Il Quality threshold (QT) clustering di (Heyer, Kruglyak e Yooseph 1999) è un algoritmo di clustering di partizionamento originariamente proposto per il clustering dei geni. L'obiettivo dell'algoritmo è trovare cluster con qualità garantita. Invece di specificare K, numero di cluster, QT utilizza il diametro massimo del cluster come parametro.

L'idea di base di QT è la seguente: formare un cluster candidato iniziando con un punto
casuale e aggiungere iterativamente altri punti, con ogni iterazione ,si aggiunge il punto che
minimizza l'aumento del diametro del cluster. Il processo continua fino a quando non è
possibile aggiungere alcun punto senza superare la soglia del diametro. Se supera la soglia,
si forma un secondo cluster candidato iniziando con un punto e ripetendo la procedura.
