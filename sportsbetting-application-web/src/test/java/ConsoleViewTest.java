import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.view.ConsoleIO;
import com.epam.training.sportsbetting.view.ConsoleView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsoleViewTest {

    private ConsoleView underTest;

    private Player testPlayer;

    @Mock
    private ConsoleIO consoleIO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        underTest = new ConsoleView(consoleIO);
        testPlayer = new Player("testEmail","pwd","testName",new BigDecimal(0), Currency.EUR);
    }

    @Test
    public void testReadCredentialsShouldAskForTheUsersEmailAndPassword(){
        //GIVEN
        String askForEmail = "> Enter player email address:";
        String expectedEmail = "testEmail";
        String askForPassword = "> Enter player password:";
        String expectedPassword = "testpwd";
        //WHEN
        underTest.readCredentials();
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedEmail);
        String actualEmail = consoleIO.nextLine();
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedPassword);
        String actualPassword = consoleIO.nextLine();
        //THEN
        BDDMockito.verify(consoleIO).println(askForEmail);
        Assertions.assertEquals(expectedEmail,actualEmail);
        BDDMockito.verify(consoleIO).println(askForPassword);
        Assertions.assertEquals(expectedPassword,actualPassword);
    }

    @Test
    public void testReadCredentialsShouldCreateNewUserWithCorrectVariables(){
        //GIVEN
        String expectedEmail = "testEmail";
        String expectedPassword = "testpwd";
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedEmail).willReturn(expectedPassword);
        //WHEN
        User actualUser = underTest.readCredentials();
        //THEN
        Assertions.assertEquals(expectedEmail,actualUser.getEmail());
        Assertions.assertEquals(expectedPassword,actualUser.getPassword());
    }

    @Test
    public void testPrintWelcomeMessageShouldPrintOutWelcomeMessage(){
        //GIVEN
        String expected = "> Welcome testName\n" +
                "> Your balance is 0 EUR";
        //WHEN
        underTest.printWelcomeMessage(testPlayer);
        //THEN
        BDDMockito.verify(consoleIO).println(expected);
    }

    @Test
    public void testPrintOutcomeShouldPrintOutBetsAndOutcomes(){
        //GIVEN
        List<Bet> testBetList = new ArrayList<>();
        List<Outcome> testOutcomeList = new ArrayList<>();
        testOutcomeList.add(new Outcome());
        testOutcomeList.add(new Outcome());
        testBetList.add(new Bet(new FootballSportEvent(),"testDescription1",testOutcomeList));
        testBetList.add(new Bet(new TennisSportEvent(),"testDescription2",testOutcomeList));
        String expectedFirst = "> 1: Sport event: null (start: null), Bet: testDescription1, Outcome: null, Actual odd: null.";
        String expectedSecond = "> 2: Sport event: null (start: null), Bet: testDescription1, Outcome: null, Actual odd: null.";
        String expectedThird = "> 3: Sport event: null (start: null), Bet: testDescription2, Outcome: null, Actual odd: null.";
        String expectedFourth = "> 4: Sport event: null (start: null), Bet: testDescription2, Outcome: null, Actual odd: null.";
        //WHEN
        underTest.printOutcome(testBetList);
        //THEN
        BDDMockito.verify(consoleIO).println(expectedFirst);
        BDDMockito.verify(consoleIO).println(expectedSecond);
        BDDMockito.verify(consoleIO).println(expectedThird);
        BDDMockito.verify(consoleIO).println(expectedFourth);
    }

    @Test
    public void testSelectOutcomeShouldPrintOutAndAskForChosenBetInput(){
        //GIVEN
        List<Bet> testBetList = new ArrayList<>();
        List<Outcome> testOutcomeList = new ArrayList<>();
        testOutcomeList.add(new Outcome());
        testOutcomeList.add(new Outcome());
        testBetList.add(new Bet(new FootballSportEvent(),"testDescription1",testOutcomeList));
        testBetList.add(new Bet(new TennisSportEvent(),"testDescription2",testOutcomeList));
        String askForBet = "> What do you want to bet on? (choose a number or press q for quit)";
        String expectedNumber = "2";
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedNumber);
        //WHEN
        underTest.selectOutcome(testBetList);
        String actualNumber = consoleIO.nextLine();
        //THEN
        BDDMockito.verify(consoleIO).println(askForBet);
        Assertions.assertEquals(expectedNumber,actualNumber);
    }

    @Test
    public void testSelectOutcomeShouldTheSelectedOutcomeWhenItsNotq(){
        //GIVEN
        List<Bet> testBetList = new ArrayList<>();
        List<Outcome> testOutcomeList = new ArrayList<>();
        testOutcomeList.add(new Outcome());
        testOutcomeList.add(new Outcome());
        testBetList.add(new Bet(new FootballSportEvent(),"testDescription1",testOutcomeList));
        testBetList.add(new Bet(new TennisSportEvent(),"testDescription2",testOutcomeList));
        String expectedOutcomeNumber = "2";
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedOutcomeNumber);
        //WHEN
        Outcome actualOutcome = underTest.selectOutcome(testBetList);
        //THEN
        Assertions.assertEquals(testOutcomeList.get(Integer.parseInt(expectedOutcomeNumber)-1),actualOutcome);
    }

    @Test
    public void testSelectOutcomeShouldReturnNullWhenSelectedOutcomeIsq(){
        //GIVEN
        List<Bet> testBetList = new ArrayList<>();
        List<Outcome> testOutcomeList = new ArrayList<>();
        testOutcomeList.add(new Outcome());
        testOutcomeList.add(new Outcome());
        testBetList.add(new Bet(new FootballSportEvent(),"testDescription1",testOutcomeList));
        testBetList.add(new Bet(new TennisSportEvent(),"testDescription2",testOutcomeList));
        String expectedOutcomeNumber = "q";
        BDDMockito.given(consoleIO.nextLine()).willReturn(expectedOutcomeNumber);
        //WHEN
        Outcome actualOutcome = underTest.selectOutcome(testBetList);
        //THEN
        Assertions.assertNull(actualOutcome);
    }

    @Test
    public void testRequestAmountForBetShouldPrintAndAskForRequestedAmount(){
        //GIVEN
        String askForBetAmount = "> What amount do you wish to bet on it?";
        BigDecimal expectedAmount = new BigDecimal(100);
        //WHEN
        underTest.requestAmountForBet();
        BDDMockito.given(consoleIO.nextBigDecimal()).willReturn(expectedAmount);
        BigDecimal actualAmount = consoleIO.nextBigDecimal();
        //THEN
        BDDMockito.verify(consoleIO).println(askForBetAmount);
        Assertions.assertEquals(expectedAmount,actualAmount);
    }

    @Test
    public void testRequestAmountForBetShouldReturnTheRequestedAmount(){
        //GIVEN
        BigDecimal expectedAmount = new BigDecimal(100);
        BDDMockito.given(consoleIO.nextBigDecimal()).willReturn(expectedAmount);
        //WHEN
        BigDecimal actualAmount = underTest.requestAmountForBet();
        //THEN
        Assertions.assertEquals(expectedAmount,actualAmount);
    }

    @Test
    public void testPrintLowBalanceMessageShouldPrintOutLowBalanceMessage() {
        //GIVEN
        String printLowBalanceMessage = "You don't have enough money, your balance is 0EUR";
        //WHEN
        underTest.printLowBalanceMessage(testPlayer);
        //THEN
        BDDMockito.verify(consoleIO).println(printLowBalanceMessage);
    }

    @Test
    public void testPrintWagerSavedShouldPrintOutTheSavedWagers(){
        //GIVEN
        Wager testWager = new Wager(new Player(),new Outcome("testDescription",
                new Bet(new FootballSportEvent(),"testDescription",new ArrayList<Outcome>()), new BigDecimal(2),false),
                new BigDecimal(100),Currency.EUR, LocalDateTime.now(),false,false);
        String savedWagerMessage = "> Wager testDescription=testDescription of null [odd: 2, amount: 100] saved!";
        String playersBalanceMessage = "> Your balance is null null";
        //WHEN
        underTest.printWagerSaved(testWager);
        //THEN
        BDDMockito.verify(consoleIO).println(savedWagerMessage);
        BDDMockito.verify(consoleIO).println(playersBalanceMessage);
    }

    @Test
    public void testPrintResultsShouldPrintOutTheResults(){
        //GIVEN
        List<Wager> testWagers = new ArrayList<>();
        Wager testWager1 = new Wager(testPlayer,new Outcome("testDescription1",
                new Bet(new FootballSportEvent(),"testDescription1",new ArrayList<Outcome>()), new BigDecimal(1),false),
                new BigDecimal(100),Currency.EUR, LocalDateTime.now(),false,false);
        Wager testWager2 = new Wager(testPlayer,new Outcome("testDescription2",
                new Bet(new FootballSportEvent(),"testDescription2",new ArrayList<Outcome>()), new BigDecimal(2),false),
                new BigDecimal(50),Currency.USD, LocalDateTime.now(),false,false);
        testWagers.add(testWager1);
        testWagers.add(testWager2);

        String resultMessage = "> Results:";
        String wagerResultMessageFirst = "> Wager 'testDescription1=testDescription1' of null [odd: 1, amount: 100], win: false";
        String wagerResultMessageSecond = "> Wager 'testDescription2=testDescription2' of null [odd: 2, amount: 50], win: false";
        String playersNewBalanceMessage = "> Your new balance is 0 EUR";

        //WHEN
        underTest.printResults(testPlayer,testWagers);
        //THEN
        BDDMockito.verify(consoleIO).println(resultMessage);
        BDDMockito.verify(consoleIO).println(wagerResultMessageFirst);
        BDDMockito.verify(consoleIO).println(wagerResultMessageSecond);
        BDDMockito.verify(consoleIO).println(playersNewBalanceMessage);
    }

}
