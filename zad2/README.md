
Sterowanie zmianą przeglądarki jest zaimplementowane na poziomie matody setup(), żeby zmienić przeglądarkę na której chemy odpalić testy wystarczy do zmiennej browser przypisać jedną z dopuszczalnych wartości (chrome, firefox lub Edge)

SeleniumBasicTest:
1. Otwieramy stronę https://www.selenium.dev/selenium/web/web-form.html
2. Sprawdzamy czy ma ona oczekiwany tytuł
3. Pobieramy kilka elementów z html i sprawdzamy czy mają one oczekiwane wartości
4. Ustawiamy kilka wartości i wysyłamy formularz
5. Sprawdzamy czy nasz formularz został wysłany

SeleniumLoginTest:
Tutaj mamy cztery krótkie scenariusze.
1. Logowanie przy użyciu poprawnych danych.
2. Logowanie z niepoprawnym loginem
3. Logowanie z niepoprawnym hasłem
4. Logowanie z poprawnymi danymi a następnie wypełnienie formularza dodającego nowego klienta oraz weryfikacja czy formularz został przekazany

SeleniumShopTest:
1. Dodajemy do koszyka trzy artykuły.
2. Weryfikujemy czy liczni dodanych artykułów pokazuje poprawną wartość.
3. Usuwamy artykuł z koszyka i ponownie weryfikujemy poprawność działania licznika.
4. Przechodzimy do koszyka i weryfikujemy czy zgadza się ilość dodanych przez nas artykułów.
5. Przechodzimy do następnego kroku.
6. Uzupełniamy wymagane inputy.
7. Przechodzimy do podsumowania.
8. Weryfikujemy czy wartość rachunku jest poprawna.
9. Finalizujemy transakcję.

SeleniumFileUploaderTest
1. Ładujemy plik pdf.
2. Sprawdzamy czy pojawiła się strona z sukcesem.
3. Sprawdzamy czy pokazywana jest poprawna nazwa pliku.
