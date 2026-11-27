## Felsökning:

Jag behövde göra en wipedata på emulatorn när min emulator slutade starta min program. När min emulator slutade starta min app, gjorde jag git checkout till tidigare versioner och prövade att att starta appen då.
När det löste inte mitt problem så sökte jag på nätet, först fick jag svaret att ändra mina debug inställningar vilket löste inte mitt problem provade, sen fick jag svaret att rensa emulatorns data vilket fungerade.
Jag förstod inte att var filtered och shown skulle finnnas utanför viewmodel så appen fungerade inte tills jag insåg att varken av dessa variabler var del av uistate eller viewmodellen.

## Reflektioner
Jag använde mest webben och studieguiden som kursen har gett oss. På webben använde jag stackoverflow, developer.android/ jetpack compose dokumentationen och kotlin dokumentationen.
