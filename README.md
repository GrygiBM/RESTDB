# RESTDB


Przyk³ad przedstawiaj¹cy uzycie REST w powi¹¿aniu z baz¹ danych SQlite

Wykorzystane nastêpuj¹ce frameworki

Jersey
Guice
Jetty 
EclipseLink

do testów

JUnit, RestAssured

Postaæ JSON dla wniosku 
Np.
{"id":1,"stan":1,"nazwa":"Wniosek startowy","tresc":"Przygotowanie wniosku do bazy danych danych ","numer":null,"info":null}

Stan jako liczba z zakresu 
CREATED(1),
VERIFIED(2),
ACCEPTED(3),
PUBLISHED(4),
REJECTED(5),
DELETED(6);

Np  dane 
Dla PUT 
CREATED:{"id":1,"stan":1,"nazwa":"Wniosek startowy","tresc":"Przygotowanie wniosku do bazy danych danych ","numer":null,"info":null}


Dla POST
PUBLISHED: {"id":1,"stan":4,"nazwa":"Wniosek startowy","tresc":"Przygotowanie wniosku do bazy danych danych ","numer":null,"info":null}

Wykorzystano serwer Jetty st¹d mo¿liwoœæ bezpoœredniego uruchomiania
endPointy:

http://localhost:8080/api/wnioski
lista wniosków:GET
dodatkowo parametry  ?Nazwa={parmaetr}&&stan={parametr}
Nowy wniosek :PUT

http://localhost:8080/api/wnioski/{id}
Dane konkretnego wniosku: GET
Zmiana stanu wniosku :POST

http://localhost:8080/api/historiazmian
Lista historii zmian stanów dla wniosku

http://localhost:8080/api/historiazmian/{id}
Dane konkretnej zmiany

http://localhost:8080/api/historiazmian/wniosek/{id}
historia zmian dla konkretnego wniosku
