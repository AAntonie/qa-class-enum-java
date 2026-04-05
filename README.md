![BrowserConfig QA CI - Java 23 - Testing Enums, Constructor Chaining & Factory Methods](https://github.com/aantonie/qa-class-enum-java/actions/workflows/ci.yml/badge.svg)

**Tema BrowserConfig - Java**  
Această temă demonstrează crearea și utilizarea unei clase `BrowserConfig` care modelează configurația unui browser pentru teste automate, demonstrând utilizarea conceptelor de **enum**, **constructori supraîncărcați cu this()**, **atributul constructorName** și **metode statice "factory"**.

---

## BrowserType.java

Această clasă este un **enum** care definește tipurile de browsere disponibile în proiect:

```java
public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE
}
```

**Avantajul folosirii unui enum:**

- Asigură valori fixe și sigure pentru tipurile de browser
- Evită erori de tipar sau valori invalide

---

## BrowserConfig.java

Clasa `BrowserConfig` stochează configurația unui browser și demonstrează **constructor chaining**, utilizarea atributului `constructorName` și metode statice "factory".

### Constructori

1. **Constructor complet**
   - Inițializează toate câmpurile: `browser`, `version`, `isHeadless`
   - Setează `constructorName` pentru a indica ce constructor a fost folosit

```java
public BrowserConfig(BrowserType browser, String version, boolean isHeadless) {
    this.browser = browser;
    this.version = version;
    this.isHeadless = isHeadless;
    this.constructorName = "Constructor 1 (browser, version, headless)";
}
```

2. **Constructor cu browser și versiune**
   - Apelează constructorul complet folosind **this()**
   - Setează `isHeadless` implicit la `false`
   - Setează `constructorName`

```java
public BrowserConfig(BrowserType browser, String version) {
    this(browser, version, false); // chaining la constructorul complet
    this.constructorName = "Constructor 2 (browser, version)";
}
```

3. **Constructor cu browser doar**
   - Apelează constructorul cu browser + versiune folosind **this()**
   - Setează versiunea la "latest"
   - `isHeadless` este preluat din constructorul precedent
   - Setează `constructorName`

```java
public BrowserConfig(BrowserType browser) {
    this(browser, "latest"); // chaining la constructorul cu 2 parametri
    this.constructorName = "Constructor 3 (browser)";
}
```

### Metodă statică "factory"

- Creează o configurație Chrome implicită
- Folosește intern **Constructor 1**
- Este **statică**, deci poate fi apelată fără a crea un obiect BrowserConfig
- Setează `constructorName` pentru a indica metoda folosită

```java
public static BrowserConfig createDefaultChromeConfig() {
    BrowserConfig cfg = new BrowserConfig(BrowserType.CHROME, "default", true);
    cfg.constructorName = "Factory Method (createDefaultChromeConfig -> Constructor 1)";
    return cfg;
}
```

### Metodă de afișare

- `afiseazaConfig()` afișează configurația într-un format tabelar, inclusiv ce constructor sau metodă a fost folosită (atributul `constructorName`)

```java
public void afiseazaConfig() {
    System.out.printf("| Browser: %-7s | Version: %-7s | Headless: %-5s | Used: %s%n",
            browser, version, isHeadless, constructorName);
}
```

---

## TestConfigRunner.java

Clasa `TestConfigRunner` demonstrează utilizarea tuturor constructorilor din `BrowserConfig` și a metodei statice "factory", precum și afișarea configurațiilor în consolă.

### Ce face codul

1. Creează un **array de obiecte `BrowserConfig`** folosind:
   - **Constructorul 1:** toate câmpurile (`browser`, `version`, `isHeadless`)
   - **Constructorul 2:** `browser` + `version` (headless implicit `false`)
   - **Constructorul 3:** doar `browser` (versiune implicită "latest", headless preluat din constructorul anterior)
   - **Metoda statică `createDefaultChromeConfig()`**: creează o configurație Chrome implicită folosind intern constructorul 1

```java
BrowserConfig[] configs = {
    new BrowserConfig(BrowserType.CHROME, "latest", true), // Constructor 1
    new BrowserConfig(BrowserType.FIREFOX, "80"),          // Constructor 2
    new BrowserConfig(BrowserType.EDGE),                   // Constructor 3
    BrowserConfig.createDefaultChromeConfig()              // Metoda statică "factory"
};
```

2. Parcurge fiecare obiect din array și **afișează configurația** folosind metoda `afiseazaConfig()`:

```java
for (BrowserConfig config : configs) {
    config.afiseazaConfig();
}
```

### Exemplu de output

```text
| Browser: CHROME   | Version: latest  | Headless: true  | Used: Constructor 1 (browser, version, headless)
| Browser: FIREFOX  | Version: 80      | Headless: false | Used: Constructor 2 (browser, version)
| Browser: EDGE     | Version: latest  | Headless: false | Used: Constructor 3 (browser)
| Browser: CHROME   | Version: default | Headless: true  | Used: Factory Method (createDefaultChromeConfig -> Constructor 1)
```
