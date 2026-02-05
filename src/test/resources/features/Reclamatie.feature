Feature: Reclamatie

  @Reclamatie
  Scenario: Depune Reclamatie protectia consumatorilor
    Given utilizatorul se logeaza pe edemocratie
    When utilizatorul initiaza procesul de creare a unei 'Reclamatii'
    And utilizatorul completeaza pasul 1 cu datele:
      | phoneNumber | 0790631237         |
      | email       | dbutuc91@gmail.com |
    And utilizatorul completeaza pasul 2 cu datele:
      | companyName    | SC Test SRL        |
      | companyAddress | 12345678           |
      | companyEmail   | dbutuc91@gmail.com |
    And utilizatorul completeaza pasul 3 cu datele:
      | typeOfIssue    | Siguranța si calitatea        |
      | issueCategory  | Practici comerciale incorecte |
      | selectIssue    | Diferență de preț, raft/casă  |
      | purchaseDate   | Wednesday, 04 March           |
      | desiredOutcome | Inlocuire produs              |

