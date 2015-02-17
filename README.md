# Netzwerk-Demo-Game

##Überblick

Ganz einfaches (sinnloses) Netzwerk-Spiel als Demonstration eines Spiel-Servers. 
- Einen *NetworkGameServer* auf einem beliebigen Rechner im Netzwerk starten.
- Zwei *MyGame* auf zwei verschiedenen Rechnern starten und mit dem Server verbinden.
- Beide Spieler steuern mit den Pfeiltasten. 

**Prinzip:** 
Jede Zustands-Änderung jedes Clients wird erst an den Server geschickt. 
Anschließend gibt der Server diese Änderungs-Wünsche an beide Clients weiter. 
Die Clients führen die Änderungs-Wünsche erst aus, nachdem sie vom Server kommen.
