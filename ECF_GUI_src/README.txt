E ovako ovo je tmp i bit ce delitano kad zavrsimo sa guiem tako da cu pisat na hrv.
Poanta ovoga je da si pisemo napomene.

Pa da pocem malo sam mljenjao ono sto je jakobovic rekao tojest dodao sam user comment kao String u algGenReg4Writing, pa se mora i na to paziti.
Kalse koje sluze za pisanja i citanje xmlwriting i xmlreading imaju static metode i jedine metode koje ti treas koristiti za gui imaju javaDoc.
Izmjenio sam detect os tako da vraca pristup dobroj konzoli.
Napravio sam rename algGenRegList->algGenRegInit algGenReg4Writing->algGenRegUser  

E rijesio sam ti citanje log filova. Tako da sad imas genotipe koje mozes pokazati. Nalaze se u paketu ...log.genotypes
Imas one standardne bitString, floatingPoint, jedino jos tree nije implementiran jer ga ne kuzim, uglavnom ovako svi izgledaju:
	<BitString size="20">11111111111111111111</BitString>
	<FloatingPoint size="10">	4.8265	49.4629	5.62914	-36.462	-29.3207	45.764	19.2099	38.2504	6.66677	-31.3074</FloatingPoint>
	<Tree size="28">sin / + / * n X / n X / + X n cos X / cos / n X + * n Y * X Y </Tree>
	<Binary size="6">	-4.52716	8.15431	-7.40975	0.0973082	5.00942	5.75442</Binary>
	<Permutation size="20">	6	15	5	3	7	11	4	0	18	16	2	19	12	8	10	1	13	17	14	9</Permutation>
E sad ja sam ti njih sve rijesio preko apstraktGenotipa i apstraktne generičke metode getValues koja ti vrača listu nečega i onda ti prikauj tu listu kako hoces.
U ...log.reader Test imas primjer jednog bitStringa kao poje integera, pa mozes prouciti.


Evo metoda koja provjerava dali file postoji :
public boolean doesExist(String file) {
		File f = new File(file);
		return f.exists();
	}
