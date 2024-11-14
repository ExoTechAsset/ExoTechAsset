# ExoTechAsset

## Table of Contents

- [About](#about)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## About
ExoTechAsset provides a streamlined solution for asset management and inventory tracking, designed to simplify workflows and reduce labor costs through automation. Unlike complex and costly systems, ExoTechAsset focuses on essential features, lowering the entry barrier and making it easier for businesses to manage assets efficiently.

## Features

- **Manage Assets**: Provides asset management functionality, including adding, deleting, modifying, reading, and auditing assets. Each asset includes an asset ID, status, assignee, audit date, location, description, and action.
- **Filter Assets**: Offers filtering functionality, allowing users to quickly select assets that meet specific criteria.
- **Export/Import Assets**: Allows for the import and export of assets using a specified CSV format.
- **Generate Report**: Generates various reports and charts using the filtering functionality, such as asset audit records, asset location bar charts, asset custodian bar charts, and asset status pie charts.

## Installation

1. Ensure you have [Git](https://git-scm.com/) and [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed.
2. Clone this repository:

   ```bash
   git clone https://github.com/ExoTechAsset/ExoTechAsset.git
   ```

3. Navigate to the project directory and install dependencies:

   ```bash
   cd ExoTechAsset
   ./gradlew clean build
   ```

## Usage

To start the project, run:

```bash
./gradlew bootRun
```

Then open your browser and go to `http://localhost:8080` to access the application.

## License

This project is licensed under the [MIT License](LICENSE).
