**Navigation:** | [Home](./) | [Code Review](code-review) | [Software Design](software-design) | [Algorithms](algorithms) | [Databases](databases)

# Databases Artifact
This artifact demonstrates database design principles through the enhancement of an existing project.

## Summary
This is an artifact titled "Animal Shelter", created for the course CS 340 Client/Server Development in April 2025. The artifact showcases basic CRUD (Create, Read, Update, Delete) functionality with a database in a Python-MongoDB setting. Its original incarnation contained several issues, including hard-coded user credentials, edge case risks such as unintentionally modifying or deleting large sets of data, and inefficient data retrieval. While the user interface limited records displayed, the backend did not enforce limits, which could lead to performance problems or crashes. The enhanced version addresses each of these issues using environment variables for user credential matching instead, additional checks for Update and Delete functions, and pagination functionality for the Read function to optionally limit the number of records retrieved from the database. This artifact demonstrates my skills in defensive programming, secure data handling, and database efficiency considerations.

## Code Files
- Original code: [View folder](artifacts/animal-shelter/AnimalShelter_old/)
- Enhanced code: [View folder](artifacts/animal-shelter/AnimalShelter_enhanced_Databases/)
