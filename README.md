# tekCode

This is an Epitech coding style checker made in Java 8.

It is up to date with the Epitech Coding Style pdf version 2.85

If you have any suggestions, bug fixes or improvements in any way, feel free to issue/pull request.

## How to use

Jar version :
```
$> java -jar tekCode.jar $project_directory
```

Standalone executable:
```
$> ./tekCode $project_directory
```

## Coding-style errors

### Captions
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> = useless or not implemented<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> = major rule<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> = minor rule<br/>
<img alt="Info" src="/icons/info.png" width="12" height="12"/> = info (or implicit) rule<br/>

### Rules
<img alt="Useless" src="/icons/not_supported.png" width="12" height="12"/> O1 -- Delivery folder should only have files required for compilation<br/>
<img alt="Useless" src="/icons/not_supported.png" width="12" height="12"/> O2 -- Sources should be .c or .h<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> O3 -- Source file should match an entity<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> O4 -- File names should be snake_case<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> G1 -- Standard file header of the school<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> G2 -- One empty line between functions<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> G3 -- Indentation of pre-processor directives<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> G4 -- Global variables should be const<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> G5 -- Static globals<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> F1 -- Single reponsability principle<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> F2 -- Functions should be snake_case<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> F3 -- 80 columns<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> F4 -- 20 line functions<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> F5 -- Function declaration should be in ISO/ANSI C syntax<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> F6 -- No comments inside a function<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> L1 -- One statement per line<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> L2 -- Tabs for indentation<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> L3 -- Spaces after a keyword<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> L4 -- Curly brackets<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> L5 -- Variable declaration<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> L6 -- Line jumps<br/>
<img alt="Major" src="/icons/major.png" width="12" height="12"/> V1 -- Variable name should be snake_case, typedefs are ended with "\_t" and macros should be in screaming SNAKE_CASE<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> V2 -- Structures should form a coherent entity<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> V3 -- Pointers on variable name<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> C1 -- Conditional branching<br/>
<img alt="Info" src="/icons/info.png" width="12" height="12"/> C2 -- Chained ternaries<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> C3 -- No goto's<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> A1 -- Constant pointers<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> A2 -- Most accurate type possible<br/>
<img alt="Unsupported" src="/icons/not_supported.png" width="12" height="12"/> H1 -- Header file content<br/>
<img alt="Minor" src="/icons/minor.png" width="12" height="12"/> H2 -- Header protected from double-inclusion<br/>
<img alt="Useless" src="/icons/not_supported.png" width="12" height="12"/> H3 -- No macros for constants, one statement<br/>
