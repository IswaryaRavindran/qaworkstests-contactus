Feature:Contact Us Page
  As an end user
  I want a contact us page
  So that I can find out more about QAWorks exciting services!!

  @test
  Scenario Outline:Valid Submission
    Given I am on the QAWorks "<url>"
    When I navigate to contact page
    Then I should be able to contact QAWorks with the "<Name>","<Email>","<Subject>","<Message>"

    Examples:
      | Name     | Email                | Subject | Message                                   |
      | j.Bloggs | j.Bloggs@qaworks.com | Enquiry | please contact me I want to find out more |

