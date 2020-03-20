Feature: List of Resources

  Scenario Outline: action to list resources
    Given user executes API call to get list of resources "<name>","<color>" and validate body's content type,header,status code
    Examples:
      | name           | color   |
      | cerulean       | #98B2D1 |
      | fuchsia rose   | #C74375 |
      | true red       | #BF1932 |
      | aqua sky       | #7BC4C4 |
      | tigerlily      | #E2583E |
      | blue turquoise | #53B0AE |
      | and dollar     | #DECDBE |
      | chili pepper   | #9B1B30 |
      | blue iris      | #5A5B9F |
      | mimosa         | #F0C05A |
      | turquoise      | #45B5AA |
      | honeysuckle    | #D94F70 |












