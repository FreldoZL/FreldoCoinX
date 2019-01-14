pragma solidity ^0.5.2;

import "./NewFreldoToken.sol";
import "./NewOwnable.sol";

contract NewFreldoCrowdsaleBase is NewOwnable {
  
  using SafeMath for uint256;

  // The token being sold
  NewFreldoToken public token;

  uint256 public  maxAmmount;
  uint256 public  mintedAmmount;

  /**
   * Event for token purchase logging
   * @param purchaser who paid for the tokens
   * @param beneficiary who got the tokens
   * @param value weis paid for purchase
   * @param amount amount of tokens purchased
   */
  event TokenPurchase(
    address indexed purchaser,
    address indexed beneficiary,
    uint256 value,
    uint256 amount
  );

  /**
   * @param _token Address of the token being sold
   */
  constructor(NewFreldoToken _token uint256 _maxAmmount) public NewOwnable() {
    require(address(_token) != address(0));

	maxAmmount = _maxAmount;
    token = _token;
	mintedAmmount = 0;
  }

  /**
   * @param _beneficiary Address performing the token purchase
   * @param _ammount defines token ammount
   */
  function mintTokens(address _beneficiary, uint256 _ammount) onlyOwner public {
	
	_require(Math.Add(_ammount, _mintedAmmount) <= maxAmmount));
  
    _preValidatePurchase(_beneficiary, _ammount);

    // calculate token amount to be created
    uint256 tokens = _ammount;
    
    _processPurchase(_beneficiary, tokens);
    
	emit TokenPurchase(
      msg.sender,
      _beneficiary,
      _ammount,
      tokens
    );

    _updatePurchasingState(_beneficiary, _ammount);
    
	_postValidatePurchase(_beneficiary, _ammount);
  }

  // -----------------------------------------
  // Internal interface (extensible)
  // -----------------------------------------

  /**
   * @dev Validation of an incoming purchase. Use require statements to revert state when conditions are not met. Use super to concatenate validations.
   * @param _beneficiary Address performing the token purchase
   * @param _weiAmount Value in wei involved in the purchase
   */
  function _preValidatePurchase(
    address _beneficiary,
    uint256 _weiAmount
  )
    internal
  {
    require(_beneficiary != address(0));
    require(_weiAmount != 0);
  }

  /**
   * @dev Validation of an executed purchase. Observe state and use revert statements to undo rollback when valid conditions are not met.
   * @param _beneficiary Address performing the token purchase
   * @param _weiAmount Value in wei involved in the purchase
   */
  function _postValidatePurchase(
    address _beneficiary,
    uint256 _weiAmount
  )
    internal
  {
    // optional override
  }

  /**
  * Mint and deliver tokens to _beneficiary address
  *
  *
  */
  function _deliverTokens(
    address _beneficiary,
    uint256 _tokenAmount
  )
    internal
  {
    require(MintableToken(token).mint(_beneficiary, _tokenAmount));
  }


  /**
   * @dev Executed when a purchase has been validated and is ready to be executed. Not necessarily emits/sends tokens.
   * @param _beneficiary Address receiving the tokens
   * @param _tokenAmount Number of tokens to be purchased
   */
  function _processPurchase(
    address _beneficiary,
    uint256 _tokenAmount
  ) internal
  {
    _deliverTokens(_beneficiary, _tokenAmount);
  }

  /**
   * @dev Override for extensions that require an internal state to check for validity (current user contributions, etc.)
   * @param _beneficiary Address receiving the tokens
   * @param _weiAmount Value in wei involved in the purchase
   */
  function _updatePurchasingState(
    address _beneficiary,
    uint256 _weiAmount
  )
    internal
  {
    // optional override
  }
 
}

contract NewFreldoCrowdsale is 
     NewFreldoCrowdsaleBase   {
	
	constructor()
		public  NewFreldoCrowdsaleBase( new NewFreldoToken(), 8000000000000000000000000000)
		{}
	}
