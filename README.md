# Projekt-Inzynieria-Oprogramowania

![Build status](https://travis-ci.org/DominikKossinski/Projekt-Inzynieria-Oprogramowania.svg?branch=master)

Podsumowanie pierwszego sprintu:<br/>
<ul>
 <li>
   Co jako zespół chcemy zacząć robić:
   <ul>
      <li> Działać efektywniej</li>
    
   </ul>
 </li>
 <li>
   Co jako zespół powinniśmy przestać robić:
   <ul>
    <li> <h2><b>Zwlekać z akceptowaniem pull request'ów</b></h2></li>
   </ul>
 </li>
 <li>
   Co jako zespół powinniśmy kontynuować robić: 
   <ul>
      <li> Komentować kod oraz pisać testy</li>
      <li> Rozmawiać o tym co nam się podoba a co nie</li>
      <li> Pomagać sobie nawzajem</li>
      <li> Pracować regularnie</li>
   </ul>
 </li>
</ul>

<h1>Testy wydajnościowe:</h1>
Test obciążeniowy równoległego połączenia dużej ilości osób do serwera jednocześnie

<h1>Cel pomiarowy:</h1>
Monitorowanie ilości wytwarzanego kodu liczonego wraz z dokumentacją

<h2>Pomiar jakości kodu LOC</h2>
Polecenie pomiaru: <b>git ls-files | xargs wc -l</b> wykonane w folderze Projekt-Inzynieria-Oprogramowania/src/main/java<br>
 Wynik 1824 linijki kodu.
 
 <h2>Instrukcja uruchamiania projektu:</h2>
 Projekt wykorzystuje bazę danych MySQL poniżej znajdują się komendy do tworzenia jej wraz z przykładowymi danymi:<br>
 <ul>
       <li>CREATE DATABASE IF NOT EXISTS projektIo;<br></li>
       <li>USE projektIo;<br></li>
       <li>CREATE TABLE IF NOT EXISTS SKROTY(SKROT VARCHAR(255) UNIQUE NOT NULL, ROZWINIECIE VARCHAR(255) NOT NULL);<br></li>
       <li>INSERT INTO SKROTY(SKROT, ROZWINIECIE) VALUES ('moj_mail', 'mail@mail.com');<br></li>
       <li>INSERT INTO SKROTY(SKROT, ROZWINIECIE) VALUES ('moj_tel', '123456789');<br></li>
 </ul>
  Projekt wykorzystuje także zmienne środowiskowe przechowujące dane o bazie danych:<br>
  <ul>
  <li>dbUrl - link do bazy danych w przypdaku (np. jdbc:mysql://127.0.0.1:3306/projektIo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC )</li>
 <li>dbUserName - nazwa użytkownika z bazy danych, kótry ma dostęp do wyżej utworzonej relacji</li>
 <li>dbPassword - hasło do bazy danych wyżej wymienionego użytkownika</li>
 </ul>
  Projekt najlepiej uruchomić przy pomocy środowiska "Intellij idea" i w zakładce <br>
  Run -> Edit Configurations -> Spring Boot -> <nazwa_run> -> Environment variables dodać wyżej wymienione zmienne.
  <br><br>
  Alternatywnym rozwiązaniem jest zmienienie w klasie <b>ProjectioApplication</b> w metodzie <b>getJdbcTemplate()</b>
  w każdym wywołaniu <b>System.getenv('nazwa_zmiennej_środowiskowej')</b> zamienić na String o odpowiedniej wartości.
  
 
