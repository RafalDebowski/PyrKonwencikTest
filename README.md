# PyrKonwencikNew

Aplikacja stworzona na potrzeby procesu rekrutacyjnego prowadzonego przez firmę Pyrkon.

## 📱 Opis projektu

**PyrKonwencikNew** to aplikacja mobilna na platformę Android, zbudowana w oparciu o nowoczesne
komponenty Jetpack Compose oraz architekturę MVVM. Aplikacja umożliwia przeglądanie listy gości oraz
wyświetlanie szczegółów wybranego uczestnika.

Projekt powstał w ramach procesu rekrutacyjnego i służy prezentacji umiejętności technicznych,
jakości kodu oraz podejścia do projektowania UI i architektury aplikacji.

## 🛠️ Technologie

- Kotlin
- Jetpack Compose (Material 3)
- MVVM + ViewModel
- Navigation Compose
- Retrofit (klient HTTP)
- Dagger Hilt (Dependency Injection)
- AndroidX
- Gradle

## 🌐 Backend / API

Projekt nie jest aktualnie połączony z backendem – dane są trzymane w lokalnym pliku .json.

> **Uwaga:** Mimo braku aktywnego API, w projekcie zaimplementowany został klient HTTP oparty o *
*Retrofit**, co umożliwia łatwe podpięcie zewnętrznego serwisu w przyszłości.

## 📦 Struktura projektu

- `ui/` – warstwa prezentacji (Composable, ViewModel, ekrany)
- `domain/` – logika biznesowa (modele, interfejsy, use case’y)
- `data/` – źródła danych, integracja z Retrofit
- `di/` – konfiguracja Hilt (moduły DI)

## 📄 Uwagi

- Projekt służy wyłącznie celom rekrutacyjnym.

Dziękuję za możliwość zaprezentowania projektu!
# PyrKonwencikTest
