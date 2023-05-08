<img src="https://media.salonedellostudente.it/app/uploads/2020/05/07180748/bergamo-logo-700x473-1.png" width=20% height=20%></img>
# Progetto Ing. del Software - FitnessApp

## progetto svolto da:
- Roberto Bertocchi 
* Stefano Cosseddu
+ Mirko Carrara
* Francesco Peracchi
## Alcuni screenshots
<img src="https://github.com/rberto4/fitnessapp_ingsw/blob/cc254b45e06b242bb554dde10ac2816deb3b76e9/Screenshots/Screenshot_20230507_112616.png" width=20% height=20%></img>
<img src="https://github.com/rberto4/fitnessapp_ingsw/blob/cc254b45e06b242bb554dde10ac2816deb3b76e9/Screenshots/Screenshot_20230507_112436.png" width=20% height=20%></img>
<img src="https://github.com/rberto4/fitnessapp_ingsw/blob/cc254b45e06b242bb554dde10ac2816deb3b76e9/Screenshots/Screenshot_20230507_113058.png" width=20% height=20%></img>
<img src="https://github.com/rberto4/fitnessapp_ingsw/blob/cc254b45e06b242bb554dde10ac2816deb3b76e9/Screenshots/Screenshot_20230507_114321.png" width=20% height=20%></img>

## Codice, Docs e UML
il codice è presente nella directory principale della repository, su Android Studio, verrà riconosciuto in automatico il progetto selezionando la cartella clonata. link utili: 
+ [packages di classi java](https://github.com/rberto4/fitnessapp_ingsw/tree/main/app/src/main/java/com/ingsw/fitnessapp)
* [classe dedicata al Db con tabelle e query SQL](https://github.com/rberto4/fitnessapp_ingsw/tree/main/app/src/main/java/com/ingsw/fitnessapp/db)
- [classi per i test Junit](https://github.com/rberto4/fitnessapp_ingsw/tree/main/app/src/test/java/com/ingsw/fitnessapp)
* [classe per test Espresso](https://github.com/rberto4/fitnessapp_ingsw/tree/main/app/src/androidTest/java/com/ingsw/fitnessapp)
+ [file xml per UI](https://github.com/rberto4/fitnessapp_ingsw/tree/main/app/src/main/res)

Documentazione e file uml sono nella sezione [docs](https://github.com/rberto4/fitnessapp_ingsw/tree/main/docs) e [UML](https://github.com/rberto4/fitnessapp_ingsw/tree/main/UML)

## FitnessApp in breve
FitnessApp è una semplice applicazione per tenere traccia dei propri workouts, gli esercizi che si svolgono e le schede di allenamento.
L'applicazione si presenta in 3 macro-sezioni: 
| Schede |  Allenamenti  |  Esercizi |
--- | --- | ---|
|sezione dedicata all'elenco di schede e visualizzazione della più recente tramite calendario|sezione dedicata all'elenco di workouts, in cui è possbile visionare il nome, il giorno della settimana ad esso dedicato, eventuali note e chiaramente gli esercizi che andremo a fare|sezione dedicata alla lista di tutti gli esercizi presenti nell'app, 


## Guida utente e funzionalità
La navigazione tra le 3 macro sezioni, avviene tramite la **BottomNavigationBar** presente in basso, inoltre è presente un pulsante **ExtendedFloatingActionButton** che, dinamicamente rispetto alla schermata, ci riporta alle varie Activities dedicate all'aggiunta dei dati, come quella del secondo screenshot, dedicata alla creazione di un nuovo esercizio

### Schermata Esercizi
il fragment dedicato alla shcermata degli esercizi, consente all'utente di avere una vista generica di tutti gle esercizi da lui creati, posizionati in una **lista ordinata rispetto alla data di creazione**, abbiamo pensato fosse il modo migliore anche in ottica di futuri upgrade come la progressione dei carichi.
Per poter cercare rapidamente all'interno della lista, c'è la possibilità di **filtrare per gruppo muscolare** selezionando gli appositi Chip orizontali, per tipo, per esercizi preferiti o per nome tramite l'apposita barra di ricerca. L'app bar posizionata in alto, offre poi la **possibilità di rimuovere rapidamente** tutti i filtri applicati con un singolo click.
il singolo esercizio è mostrato in una **Card**, dove è presente il nome, il gruppo muscolare, i dettagli dello stesso (come le ripetizioni, il carico, le serie e il tempo di recupero). La card presenta anche un **menù a scomparsa** dove, se cliccato, mostrerà i pulsanti **modifica esercizio** o **elimina**

### Schermata Nuovo Esercizio: creazione e modifica
La schermata in questione, ci permette di aggiungere al nostro database un nuovo esercizio. La UI ci mostra in maniera dinamica i campi della "form" da compilare, con tutte le caratteristiche necessarie al tipo di esercizio, per quelli relativi alla **pesisitica** :
- Gruppo muscolare
* Nome
+ Ripetizioni
* Recupero
- Serie 
* Zavorra

per quelli relativi alla sezione **cardio** :
* Nome
+ Durata
* Difficoltà

Per facilitare l'utente, nell'inserimento del **nome**, l'app mette a disposizione un elenco di **nomi predefiniti** che vengono mostrati in base al gruppo muscolare scelto. Ciò nonostante, il **gruppo muscolare non vincola l'utente**: esso è libero di scegliere il gruppo muscolare che più preferisce per uno specifico esercizio.
Qual'ora non fosse presente, lo possiamo **creare personalizzato** tramite l'apposito **EditText**.
Anche la modifica di un esercizio richiamerà la medesima schermata, pre-compilando la form con tutti i campi attuali e permettendo il **salvataggio con le eventuali modifiche**.

### Schermata Workouts
il fragment dedicato alla **shcermata degli allenamenti**, consente all'utente di avere una vista di tutti gli allenamenti creati, ovvero l'insieme di esercizi che si svolgono **nell'arco di una seduta di allenamento**.
La lista presenta quindi una vista orizzontale sugli esercizi relativi al workout anche mostrando, in maniera chiara all'utente, l'ordine della loro esecuzione.
Vengono visualizzati in prima funzione, il giorno della settimana assegnato, un pulsante per mostrare le eventuali note, ed il tasto per richiamare il menù a scomparsa, con le funzioni **modifica ed elimina allenamento**.

### Schermata Nuovo Workout: creazione e modifica
La schermata di creazione dedicata all'allenamento, permette la scelta del **giorno della settimana** tramite l'apposito selettore, il nome e le note opzionali e naturalemente, si potranno **selezionare gli esercizi** da noi creati in precedenza o **crearne al momento**, di nuovi, per poi selezionarli in ordine di esecuzione.

### Schermata Schede
il fragment dedicato alla **shcermata delle schede**, probabilmente la schermata core dell'applicazione, consente all'utente di avere una **vista giornaliera** dei propri allenamenti visualizzando sul **calendario dedicato**, l'allenamento (uno o **molteplici** in caso di doppie sedute giornaliere) della **giornata odierna** (mostrata di default all'avvio dell'app) e di tutte quelle che rientrano nel range della scheda corrente.
Sull'appBar si possono **switchare le schede create in precedenza** (mostrate in un menù dropdown a scomparsa), oppure mostrare il menù relativo alle **note della scheda** e il tasto per **eliminarla definitivamente**.

### Schermata Nuova Scheda
La schermata di creazione dedicata alla scheda, permette di scegliere, ##per ogni giorno della settimana##, uno o più allenamenti presenti nella memoria dell'app, e di selezionare il perido di competenza relativo alla scheda che si sta creando, oltre che al nome e ad eventuali note.

## Features future
in futuro ci piacerebbe che l'app offrisse anche la possibilità di visualzzare uno storico delle progressioni sui carichi degli esercizi o in generale sui sonostri migliramenti
Anche la possibilità di sfruttarla durante l'allenamento,  con un timer dedicato ai recuperi
