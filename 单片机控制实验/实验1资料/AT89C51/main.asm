;====================================================================
; Main.asm file generated by New Project wizard
;
; Created:   �ܶ� ���� 5 2023
; Processor: AT89C51
; Compiler:  ASEM-51 (Proteus)
;====================================================================

$NOMOD51
$INCLUDE (8051.MCU)

;====================================================================
; DEFINITIONS
;====================================================================

;====================================================================
; VARIABLES
;====================================================================

;====================================================================
; RESET and INTERRUPT VECTORS
;====================================================================

      ; Reset Vector
      org   0000h
      jmp   Start

;====================================================================
; CODE SEGMENT
;====================================================================

      org   0100h
Start:	
      ; Write your code here
      MOV C, P1.0
      JNC  LABEL1
      SETB P1.7
      JMP LABEL2
LABEL1:
      CPL P1.7
LABEL2:
      CALL DELAY
      jmp START
DELAY:
   MOV R1, #0FEH
DELAY_1:
   MOV R2, #0FFH
   DJNZ R2, $
   DJNZ R1, DELAY_1
   RET
;====================================================================
      END
